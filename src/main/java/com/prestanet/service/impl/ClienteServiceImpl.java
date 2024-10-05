package com.prestanet.service.impl;


import com.prestanet.model.entity.Cliente;
import com.prestanet.repository.ClienteRepository;
import com.prestanet.service.ClienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null); // Devuelve null si no se encuentra
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(int id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return null; // Devuelve null si el cliente no existe
        }
        cliente.setIdCliente(id); // Asegúrate de que el id se mantenga
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(int id) {
        clienteRepository.deleteById(id);
    }


    @Override
    public Optional<Cliente> findByDni(String dni) {
        return clienteRepository.findByDni(dni);  // Implementar el método correctamente
    }
}