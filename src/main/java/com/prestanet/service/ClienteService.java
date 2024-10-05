package com.prestanet.service;

import com.prestanet.model.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(int id);
    Cliente save(Cliente cliente);
    Cliente update(int id, Cliente cliente);
    void delete(int id);

    /////
    Optional<Cliente> findByDni(String dni);
}
