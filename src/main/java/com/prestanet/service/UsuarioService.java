package com.prestanet.service;

import com.prestanet.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuarioPorId(int idUsuario);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO actualizarUsuario(int idUsuario, UsuarioDTO usuarioDTO);
    void eliminarUsuario(int idUsuario);
}
