package com.prestanet.service.impl;

import com.prestanet.model.entity.CronogramaPago;
import com.prestanet.repository.CronogramaPagoRepository;
import com.prestanet.service.CronogramaPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CronogramaPagoServiceImpl implements CronogramaPagoService {

    @Autowired
    private CronogramaPagoRepository cronogramaPagoRepository;

    // Crear o actualizar un CronogramaPago
    @Override
    public CronogramaPago saveCronogramaPago(CronogramaPago cronogramaPago) {
        return cronogramaPagoRepository.save(cronogramaPago);
    }

    // Obtener todos los CronogramaPagos
    @Override
    public List<CronogramaPago> getAllCronogramaPagos() {
        return cronogramaPagoRepository.findAll();
    }

    // Obtener un CronogramaPago por ID
    @Override
    public Optional<CronogramaPago> getCronogramaPagoById(int id) {
        return cronogramaPagoRepository.findById(id);
    }

    // Eliminar un CronogramaPago por ID
    @Override
    public void deleteCronogramaPagoById(int id) {
        cronogramaPagoRepository.deleteById(id);
    }
}
