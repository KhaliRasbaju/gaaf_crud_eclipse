package com.udi.gaaf.inventario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Representa los identificadores necesarios para buscar un inventario
 * específico en función de una bodega y un producto.
 *
 * @param idBodega  identificador de la bodega. Debe ser positivo y no nulo.
 * @param idProducto identificador del producto. Debe ser positivo y no nulo.
 */
public record DatosBuscarInventarioIds(
    @Positive
    @NotNull
    Long idBodega,

    @Positive
    @NotNull
    Long idProducto
) { }
