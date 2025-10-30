package com.udi.gaaf.medio_pago;

/**
 * Record que contiene los datos detallados de un medio de pago.
 *
 * @param id Identificador único del medio de pago.
 * @param referencia Código o referencia única del pago.
 * @param metodoPago Nombre del método de pago asociado.
 */
public record DatosDetalleMedioPago(
    Long id,
    String referencia,
    String metodoPago
) {}
