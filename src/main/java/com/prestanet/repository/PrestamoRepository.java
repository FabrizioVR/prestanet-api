package com.prestanet.repository;

import com.prestanet.model.entity.Cliente;
import com.prestanet.model.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    Optional<Prestamo> findTopByClienteOrderByFechaSolicitudDesc(Cliente cliente);
}
