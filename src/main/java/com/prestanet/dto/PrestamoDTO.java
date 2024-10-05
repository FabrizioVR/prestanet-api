package com.prestanet.dto;

import com.prestanet.model.enums.TipoPrestamo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoDTO {

    private int idPrestamo;

    @NotNull(message = "Monto obligatorio")
    @Positive(message = "El monto debe ser un número positivo")
    private double monto;

    @NotNull(message = "Tipo obligatorio")
    private TipoPrestamo tipoPrestamo = TipoPrestamo.UN_MES;

    @NotNull(message = "Interés obligatorio")
    @Positive(message = "El interés debe ser un número positivo")
    private double interes;

    @NotNull(message = "Fecha obligatoria")
    private LocalDate fechaSolicitud;

    @NotBlank(message = "Nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    @NotNull(message = "ID de usuario es obligatorio")
    private Integer idUsuario;  // Agregamos la ID del usuario

}
