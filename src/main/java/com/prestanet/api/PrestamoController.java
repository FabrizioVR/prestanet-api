package com.prestanet.api;


import com.prestanet.model.entity.Prestamo;
import com.prestanet.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/solicitud")
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody @Valid Prestamo prestamo) {
        Prestamo nuevoPrestamo = prestamoService.registrarSolicitud(prestamo);
        return new ResponseEntity<Prestamo>(nuevoPrestamo, HttpStatus.CREATED);
    }
}