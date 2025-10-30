package com.udi.gaaf.bodega;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Datos requeridos para el registro de una nueva bodega.
 * <p>
 * Esta clase se utiliza para recibir la información desde el cliente
 * al momento de crear una nueva bodega en el sistema.
 * </p>
 * 
 * @param nombre nombre de la bodega, no puede ser nulo ni vacío
 * @param ubicacion ubicación de la bodega, no puede ser nula ni vacía
 */
public record DatosRegistrarBodega(
    @NotNull
    @NotBlank
    String nombre,

    @NotNull
    @NotBlank
    String ubicacion
) {
}
