package com.prestanet.repository;

import com.prestanet.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar usuario por nombre de usuario y contraseña
    Usuario findByNombreUsuarioAndContraseña(String nombreUsuario, String contraseña);
}


