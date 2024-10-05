package com.prestanet.model.entity;

import com.prestanet.model.enums.TipoCLiente;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @Column(name= "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name= "dni", length = 8, nullable = false)
    private String dni;
}
