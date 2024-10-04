package com.prestanet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prestanet.model.enums.EstadoPago;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cronograma_pago")
public class CronogramaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCronograma;

    @Column(name= "fecha_cronograma", nullable = false)
    private LocalDate fechaCronograma;

    @Column(name= "monto_por_pagar", nullable = false)
    private double montoPorPagar;

    @Enumerated(EnumType.STRING)
    @Column(name= "estado_pago", nullable = false)
    private EstadoPago estadoPago = EstadoPago.EN_PROCESO;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_prestamo", referencedColumnName = "idPrestamo",
            foreignKey = @ForeignKey(name = "FK_cronograma_prestamo"))
    private Prestamo prestamo;
}
