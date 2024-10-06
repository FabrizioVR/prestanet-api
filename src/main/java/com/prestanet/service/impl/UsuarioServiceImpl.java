package com.prestanet.service.impl;

import com.prestanet.dto.UsuarioDTO;
import com.prestanet.mapper.UsuarioMapper;
import com.prestanet.model.entity.Usuario;
import com.prestanet.repository.UsuarioRepository;
import com.prestanet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioGuardado);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(usuarioMapper::toDTO).orElse(null);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO actualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuarioActualizado = usuarioOpt.get();
            usuarioActualizado.setNombreUsuario(usuarioDTO.getNombreUsuario());
            usuarioActualizado.setContraseña(usuarioDTO.getContraseña());
            Usuario usuarioGuardado = usuarioRepository.save(usuarioActualizado);
            return usuarioMapper.toDTO(usuarioGuardado);
        }
        return null;
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }


    public Usuario iniciarSesion(String nombreUsuario, String contraseña) {
        return usuarioRepository.findByNombreUsuarioAndContraseña(nombreUsuario, contraseña);
    }

}
