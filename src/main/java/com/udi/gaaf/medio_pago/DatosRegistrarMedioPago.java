package com.udi.gaaf.medio_pago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Record que contiene los datos requeridos para registrar un nuevo medio de pago.
 *
 * @param idMetodoPago ID del método de pago asociado. Debe ser positivo y no nulo.
 * @param referencia   Referencia única del pago. No puede estar en blanco.
 */
public record DatosRegistrarMedioPago(
    @Positive
    @NotNull
    Long idMetodoPago,

    @NotBlank
    String referencia
) {}
