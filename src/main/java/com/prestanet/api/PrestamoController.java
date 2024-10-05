package com.prestanet.api;


import com.prestanet.dto.PrestamoDTO;
import com.prestanet.model.entity.Prestamo;
import com.prestanet.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> registrarSolicitudPrestamo(@Valid @RequestBody PrestamoDTO prestamoDTO, @RequestParam String dniCliente) {
        prestamoService.registrarSolicitud(prestamoDTO, dniCliente);
        return ResponseEntity.ok("La solicitud de préstamo fue recibida correctamente.");
    }
}