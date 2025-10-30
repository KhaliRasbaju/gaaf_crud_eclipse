package com.udi.gaaf.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para registrar o actualizar un producto.
 *
 * @param nombre      Nombre del producto.
 * @param descripcion Descripción detallada del producto.
 * @param tipo        Tipo del producto (CACAO o INGREDIENTES_COMPLEMENTARIOS).
 */
public record DatosRegistrarProducto(
    @NotBlank
    String nombre,

    @NotBlank
    String descripcion,

    @NotNull(message = "Este campo es requerido de tipo [CACAO | INGREDIENTES_COMPLEMENTARIOS]")
    TipoProducto tipo
) {}
