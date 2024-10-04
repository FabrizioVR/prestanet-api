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

    @Column(name= "DNI", length = 8)
    private String DNI;

    @Column(name= "RUC", length = 11)
    private String RUC;

    @Enumerated(EnumType.STRING)
    @Column(name= "tipo_cliente", nullable = false)
    private TipoCLiente tipoCLiente= TipoCLiente.NATURAL;
}
