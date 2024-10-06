package com.prestanet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prestanet.model.enums.TipoPrestamo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestamo;

    @Column(name= "monto", nullable = false)
    private double monto;

    @Enumerated(EnumType.STRING)
    @Column(name= "tipo_prestamo", nullable = false)
    private TipoPrestamo tipoPrestamo =TipoPrestamo.UN_MES;

    @Column(name= "interes", nullable = false)
    private double interes;

    @Column(name= "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario",
            foreignKey = @ForeignKey(name = "FK_prestamo_usuario"))
    private Usuario usuario;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "idCliente",
            foreignKey = @ForeignKey(name = "FK_prestamo_cliente"))
    private Cliente cliente;


    // Método para calcular el interés
    public void calcularInteres() {
        if (tipoPrestamo == TipoPrestamo.UN_MES) {
            this.interes = this.monto * 0.10; // 10% de interés
        } else if (tipoPrestamo == TipoPrestamo.SEIS_MESES) {
            this.interes = this.monto * 0.20; // 20% de interés
        }
    }
}
