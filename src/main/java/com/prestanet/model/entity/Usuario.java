package com.prestanet.model.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name= "nombre_usuario", nullable = false, length = 150)
    private String nombreUsuario;

    @Column(name= "contraseña", nullable = false, length = 150)
    private String contraseña;
}
