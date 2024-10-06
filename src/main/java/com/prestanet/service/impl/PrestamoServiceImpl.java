package com.prestanet.service.impl;

import com.prestanet.dto.PrestamoDTO;
import com.prestanet.mapper.PrestamoMapper;
import com.prestanet.model.entity.Cliente;
import com.prestanet.model.entity.Prestamo;
import com.prestanet.model.enums.TipoPrestamo;
import com.prestanet.repository.ClienteRepository;
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

    @Autowired
    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, ClienteRepository clienteRepository, PrestamoMapper prestamoMapper) {
        this.prestamoRepository = prestamoRepository;
        this.clienteRepository = clienteRepository;
        this.prestamoMapper = prestamoMapper;
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

        // Guardar en la base de datos
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        // Devolver el préstamo guardado como DTO
        return prestamoMapper.toDTO(prestamoGuardado);
    }
}