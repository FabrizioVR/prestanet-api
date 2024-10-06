package com.prestanet.service.impl;

import com.prestanet.dto.PrestamoDTO;
import com.prestanet.mapper.PrestamoMapper;
import com.prestanet.model.entity.Cliente;
import com.prestanet.model.entity.CronogramaPago;
import com.prestanet.model.entity.Prestamo;
import com.prestanet.model.enums.EstadoPago;
import com.prestanet.model.enums.TipoPrestamo;
import com.prestanet.repository.ClienteRepository;
import com.prestanet.repository.CronogramaPagoRepository;
import com.prestanet.repository.PrestamoRepository;
import com.prestanet.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final ClienteRepository clienteRepository;
    private final PrestamoMapper prestamoMapper;
    private final CronogramaPagoRepository cronogramaPagoRepository;

    @Autowired
    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, ClienteRepository clienteRepository, PrestamoMapper prestamoMapper, CronogramaPagoRepository cronogramaPagoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.clienteRepository = clienteRepository;
        this.prestamoMapper = prestamoMapper;
        this.cronogramaPagoRepository = cronogramaPagoRepository;
    }


    @Override
    public PrestamoDTO registrarSolicitud(PrestamoDTO prestamoDTO, String dniCliente) {
        // Validar que el DNI tenga exactamente 8 dígitos
        if (dniCliente == null || dniCliente.length() != 8) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos.");
        }

        // Buscar cliente por DNI
        Optional<Cliente> clienteOpt = clienteRepository.findByDni(dniCliente);
        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente con DNI " + dniCliente + " no encontrado.");
        }

        // Verificar si el cliente puede solicitar otro préstamo
        LocalDate fechaUltimoPrestamo = prestamoRepository.findTopByClienteOrderByFechaSolicitudDesc(clienteOpt.get())
                .map(Prestamo::getFechaSolicitud).orElse(null);

        if (fechaUltimoPrestamo != null) {
            LocalDate fechaLimite;
            if (prestamoDTO.getTipoPrestamo() == TipoPrestamo.UN_MES) {
                fechaLimite = fechaUltimoPrestamo.plusMonths(1);
            } else { // SEIS_MESES
                fechaLimite = fechaUltimoPrestamo.plusMonths(6);
            }
            if (!LocalDate.now().isAfter(fechaLimite)) {
                throw new IllegalArgumentException("El cliente no puede solicitar otro préstamo hasta " + fechaLimite);
            }
        }

        // Asignar cliente y fecha de solicitud al préstamo
        Prestamo prestamo = prestamoMapper.toEntity(prestamoDTO);
        prestamo.setFechaSolicitud(LocalDate.now());
        prestamo.setCliente(clienteOpt.get());

        // Calcular interés
        prestamo.calcularInteres();
        if (prestamo.getInteres() <= 0) {
            throw new IllegalArgumentException("El interés debe ser un número positivo.");
        }


        // Guardar en la base de datos
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        crearCronogramaPago(prestamoGuardado);

        // Devolver el préstamo guardado como DTO
        return prestamoMapper.toDTO(prestamoGuardado);
    }
    ////////////////////////////////
    private void crearCronogramaPago(Prestamo prestamo) {
        LocalDate fechaSolicitud = prestamo.getFechaSolicitud();
        double montoConInteres = prestamo.getMonto() + prestamo.getInteres();
        int numeroDePagos = (prestamo.getTipoPrestamo() == TipoPrestamo.UN_MES) ? 1 : 6; // Cambia según el tipo de préstamo

        // Calcula la fecha base dependiendo del tipo de préstamo
        LocalDate fechaBaseCronograma = (prestamo.getTipoPrestamo() == TipoPrestamo.UN_MES) ?
                fechaSolicitud.plusMonths(1) : fechaSolicitud.plusMonths(6);

        for (int i = 0; i < numeroDePagos; i++) {
            CronogramaPago cronogramaPago = new CronogramaPago();

            // Ajustar la fecha del cronograma dependiendo del tipo de préstamo
            cronogramaPago.setFechaCronograma(fechaBaseCronograma.plusMonths(i)); // Agregar meses según el ciclo
            cronogramaPago.setMontoPorPagar(montoConInteres / numeroDePagos); // Monto dividido por el número de pagos
            cronogramaPago.setEstadoPago(EstadoPago.EN_PROCESO); // Establecer estado inicial

            // Asignar el préstamo al cronograma
            cronogramaPago.setPrestamo(prestamo);

            // Aquí debes guardar el cronogramaPago en tu repositorio de CronogramaPago
            cronogramaPagoRepository.save(cronogramaPago);
        }
    }

}