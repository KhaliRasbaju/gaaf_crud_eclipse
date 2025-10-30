package com.udi.gaaf.inventario;

import java.time.LocalDateTime;

/**
 * Representa los detalles de un inventario, incluyendo la cantidad,
 * fecha de actualización, nombre de la bodega y del producto.
 *
 * @param cantidad cantidad disponible en el inventario.
 * @param fecha fecha de la última actualización.
 * @param bodega nombre de la bodega.
 * @param producto nombre del producto.
 */
public record DatosDetalleInventario(
    Integer cantidad,
    LocalDateTime fecha,
    String bodega,
    String producto
) { }
