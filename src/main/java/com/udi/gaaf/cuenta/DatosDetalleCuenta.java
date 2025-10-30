package com.udi.gaaf.cuenta;

import com.udi.gaaf.common.DatosDetalleCommon;

/**
 * Representa el modelo de detalle para una cuenta bancaria.
 * <p>
 * Se utiliza principalmente para mostrar información de una cuenta
 * en las respuestas de la API.
 * </p>
 *
 * @param id identificador único de la cuenta
 * @param numero número de cuenta bancaria
 * @param tipo tipo de cuenta (AHORROS, CORRIENTE, etc.)
 * @param entidad información resumida de la entidad bancaria asociada
 */
public record DatosDetalleCuenta(
    Long id,
    String numero,
    TipoCuenta tipo,
    DatosDetalleCommon entidad
) {}
