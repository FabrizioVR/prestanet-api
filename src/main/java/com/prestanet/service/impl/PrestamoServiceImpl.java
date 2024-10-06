package com.prestanet.service.impl;

import com.prestanet.dto.PrestamoDTO;
import com.prestanet.mapper.PrestamoMapper;
import com.prestanet.model.entity.Cliente;
import com.prestanet.model.entity.Prestamo;

import com.prestanet.model.entity.Usuario;
import com.prestanet.repository.ClienteRepository;
import com.prestanet.repository.PrestamoRepository;
import com.prestanet.repository.UsuarioRepository;
import com.prestanet.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Prestamo registrarSolicitud(Prestamo prestamo) {
        // Buscar el cliente por DNI
        Optional<Cliente> optionalCliente = clienteRepository.findByDni(prestamo.getCliente().getDni());
        Cliente cliente = optionalCliente.orElseThrow(() ->
                new RuntimeException("Cliente no encontrado con DNI: " + prestamo.getCliente().getDni()));

        // Buscar el usuario por ID
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(prestamo.getUsuario().getIdUsuario());
        Usuario usuario = optionalUsuario.orElseThrow(() ->
                new RuntimeException("Usuario no encontrado con ID: " + prestamo.getUsuario().getIdUsuario()));

        // Asignar los datos al préstamo
        prestamo.setFechaSolicitud(LocalDate.now());
        prestamo.setUsuario(usuario);
        prestamo.setCliente(cliente);

        // Guardar el préstamo en el repositorio
        return prestamoRepository.save(prestamo);
    }
}