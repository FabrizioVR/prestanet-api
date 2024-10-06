package com.prestanet.api;

import com.prestanet.dto.PrestamoDTO;

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
        // Establecer la fecha de solicitud a la fecha actual
        prestamoDTO.setFechaSolicitud(LocalDate.now());

        // Calcular el interés basado en el tipo de préstamo
        prestamoDTO.calcularInteres();

        // Validar si el interés fue calculado correctamente
        if (prestamoDTO.getInteres() <= 0) {
            return ResponseEntity.badRequest().body("El interés calculado no es válido.");
        }

        // Llamar al servicio para registrar la solicitud
        prestamoService.registrarSolicitud(prestamoDTO, dniCliente);

        return ResponseEntity.ok(
                "La solicitud de préstamo fue recibida correctamente. Interés calculado: " + prestamoDTO.getInteres());
    }

}
