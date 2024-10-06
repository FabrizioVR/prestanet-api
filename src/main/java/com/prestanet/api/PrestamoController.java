package com.prestanet.api;


import com.prestanet.dto.PrestamoDTO;
import com.prestanet.model.enums.TipoPrestamo;
import com.prestanet.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Validated
@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @Autowired
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/solicitud")
    public ResponseEntity<String> registrarSolicitudPrestamo(@Valid @RequestBody PrestamoDTO prestamoDTO,
                                                             @RequestParam String dniCliente) {
        // Obtener el interés según el tipo de préstamo
        double interes = 0.0;

        if (prestamoDTO.getTipoPrestamo() == TipoPrestamo.UN_MES) {
            interes = 10.0; // Interés del 10%
        } else if (prestamoDTO.getTipoPrestamo() == TipoPrestamo.SEIS_MESES) {
            interes = 20.0; // Interés del 20%
        }

        // Comprobar si el interés es 0 (no debe ser 0) antes de asignar al DTO
        if (interes <= 0) {
            return ResponseEntity.badRequest().body("El interés debe ser un número positivo.");
        }

        // Asignar el interés al DTO
        prestamoDTO.setInteres(interes);

        // Establecer la fecha de solicitud (puedes establecerla como la fecha actual)
        prestamoDTO.setFechaSolicitud(LocalDate.now());

        // Llamar al servicio para registrar la solicitud
        prestamoService.registrarSolicitud(prestamoDTO, dniCliente);

        return ResponseEntity.ok("La solicitud de préstamo fue recibida correctamente.");
    }

}
