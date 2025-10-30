package com.udi.gaaf.producto;

/**
 * DTO que representa los detalles de un producto.
 *
 * @param id          Identificador único del producto.
 * @param nombre      Nombre del producto.
 * @param descripcion Descripción del producto.
 * @param tipo        Tipo del producto (CACAO o INGREDIENTES_COMPLEMENTARIOS).
 */
public record DatosDetalleProducto(
    Long id,
    String nombre,
    String descripcion,
    TipoProducto tipo
) {}
