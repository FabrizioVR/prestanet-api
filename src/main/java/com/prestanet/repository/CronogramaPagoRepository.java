package com.prestanet.repository;

import com.prestanet.model.entity.CronogramaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronogramaPagoRepository extends JpaRepository<CronogramaPago, Integer> {
    // Los métodos CRUD básicos ya están incluidos por defecto en JpaRepository
}
