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

    private int idUsuario;

    private int idCliente;

    @NotNull(message = "Monto obligatorio")
    @Positive(message = "El monto debe ser un número positivo")
    private double monto;

    @NotNull(message = "Tipo obligatorio")
    private TipoPrestamo tipoPrestamo = TipoPrestamo.UN_MES;

    // Eliminar la validación de interés obligatorio, ya que se calculará automáticamente
    private double interes;


    private LocalDate fechaSolicitud;

    @NotBlank(message = "Nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    // Método para calcular el interés basado en el tipo de préstamo
    public void calcularInteres() {
        if (this.tipoPrestamo == TipoPrestamo.UN_MES) {
            this.interes = 0.10 * monto; // 10% de interés
        } else if (this.tipoPrestamo == TipoPrestamo.SEIS_MESES) {
            this.interes = 0.20 * monto; // 20% de interés
        }
    }
}
