package com.udi.gaaf.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Modelo genérico utilizado para registrar entidades que solo requieren
 * un campo de nombre como dato esencial.
 * <p>
 * Incluye validaciones básicas mediante anotaciones de Jakarta Validation
 * para garantizar que el nombre no sea nulo ni vacío.
 * </p>
 *
 * @param nombre nombre del elemento a registrar, no puede ser nulo ni vacío
 */
public record DatosRegistrarCommon(
    @NotBlank
    @NotNull
    String nombre
) {}
