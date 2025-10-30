package com.udi.gaaf.cuenta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Modelo de datos utilizado para registrar una nueva cuenta bancaria.
 * <p>
 * Incluye validaciones para garantizar la integridad de los datos ingresados.
 * </p>
 *
 * @param numero número de la cuenta bancaria (valor positivo)
 * @param tipo tipo de cuenta, debe ser uno de los valores definidos en {@link TipoCuenta}
 * @param idEntidad identificador de la entidad bancaria asociada
 */
public record DatosRegistrarCuenta(
    @Positive
    @NotNull
    String numero,

    @NotNull(message = "No puede ser nulo debe ser [AHORROS | CORRIENTE]")
    TipoCuenta tipo,

    @Positive
    @NotNull(message = "Numero de la cuenta bancaria requerido")
    Long idEntidad
) {}
