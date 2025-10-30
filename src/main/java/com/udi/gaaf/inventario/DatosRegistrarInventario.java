package com.udi.gaaf.inventario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Representa los datos requeridos para registrar o modificar
 * un inventario en el sistema.
 *
 * @param fecha fecha de actualización del inventario.
 * @param cantidad cantidad de productos disponibles o a modificar.
 * @param idProducto identificador del producto relacionado.
 * @param idBodega identificador de la bodega relacionada.
 */
public record DatosRegistrarInventario(
    @NotNull
    LocalDateTime fecha,

    @NotNull
    @Positive
    Integer cantidad,

    @NotNull
    @Positive
    Long idProducto,

    @NotNull
    @Positive
    Long idBodega
) { }
