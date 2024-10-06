package com.prestanet.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDTO {
    private int idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreUsuario;

    @NotBlank(message = "El contraseña es obligatorio")
    private String contraseña;


}
