package com.prestanet.dto;


import com.prestanet.model.enums.EstadoPago;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CronogramaPagoDTO {

    private int idCronograma;

    private int idPrestamo;

    @NotNull(message = "La fecha del cronograma es obligatoria")
    private LocalDate fechaCronograma;

    @NotNull(message = "El monto por pagar es obligatorio")
    private double montoPorPagar;

    @NotNull(message = "El estado de pago es obligatorio")
    private EstadoPago estadoPago = EstadoPago.EN_PROCESO;
}
