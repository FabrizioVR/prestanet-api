package com.prestanet.service;

import com.prestanet.model.entity.CronogramaPago;
import java.util.List;
import java.util.Optional;

public interface CronogramaPagoService {
    CronogramaPago saveCronogramaPago(CronogramaPago cronogramaPago);
    List<CronogramaPago> getAllCronogramaPagos();
    Optional<CronogramaPago> getCronogramaPagoById(int id);
    void deleteCronogramaPagoById(int id);
}

