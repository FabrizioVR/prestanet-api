package com.prestanet.api;

import com.prestanet.model.entity.CronogramaPago;
import com.prestanet.service.CronogramaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cronograma")
@CrossOrigin(origins = "https://prestamos-insoii.onrender.com", methods = { "GET", "POST" })
public class CronogramaPagoController {

    @Autowired
    private CronogramaPagoService cronogramaPagoService;

    // Crear un nuevo CronogramaPago
    @PostMapping
    public CronogramaPago createCronogramaPago(@RequestBody CronogramaPago cronogramaPago) {
        return cronogramaPagoService.saveCronogramaPago(cronogramaPago);
    }

    // Leer todos los CronogramaPagos
    @GetMapping
    public List<CronogramaPago> getAllCronogramaPagos() {
        return cronogramaPagoService.getAllCronogramaPagos();
    }

    // Leer un CronogramaPago por ID
    @GetMapping("/{id}")
    public ResponseEntity<CronogramaPago> getCronogramaPagoById(@PathVariable int id) {
        Optional<CronogramaPago> cronogramaPago = cronogramaPagoService.getCronogramaPagoById(id);
        return cronogramaPago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un CronogramaPago existente
    @PutMapping("/{id}")
    public ResponseEntity<CronogramaPago> updateCronogramaPago(@PathVariable int id, @RequestBody CronogramaPago cronogramaPagoDetails) {
        Optional<CronogramaPago> existingCronogramaPago = cronogramaPagoService.getCronogramaPagoById(id);

        if (existingCronogramaPago.isPresent()) {
            CronogramaPago cronogramaPago = existingCronogramaPago.get();
            cronogramaPago.setFechaCronograma(cronogramaPagoDetails.getFechaCronograma());
            cronogramaPago.setMontoPorPagar(cronogramaPagoDetails.getMontoPorPagar());
            cronogramaPago.setEstadoPago(cronogramaPagoDetails.getEstadoPago());

            CronogramaPago updatedCronogramaPago = cronogramaPagoService.saveCronogramaPago(cronogramaPago);
            return ResponseEntity.ok(updatedCronogramaPago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un CronogramaPago por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCronogramaPagoById(@PathVariable int id) {
        cronogramaPagoService.deleteCronogramaPagoById(id);
        return ResponseEntity.noContent().build();
    }
}