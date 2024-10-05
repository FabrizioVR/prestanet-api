package com.prestanet.dto;

import com.prestanet.model.enums.TipoPrestamo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoDTO {

    private int idPrestamo;

    @NotNull(message = "Monto obligatorio")
    @Positive(message = "El monto debe ser un número positivo")
    private double monto;

    @NotBlank(message = "Tipo obligatorio")
    private TipoPrestamo tipoPrestamo =TipoPrestamo.UN_MES;

    @NotNull(message = "Interés obligatorio")
    @Positive(message = "El interés debe ser un número positivo")
    private double interes;

    @NotNull(message = "Fecha obligatoria")
    private LocalDate fechaSolicitud;
}
