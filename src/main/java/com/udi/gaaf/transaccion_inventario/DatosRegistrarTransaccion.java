package com.udi.gaaf.transaccion_inventario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


/**
 * Record que contiene los datos necesarios para registrar una nueva transacción de inventario.
 * Se valida con anotaciones de Jakarta Validation.
 *
 * @param tipo        Tipo de transacción [ENTRADA | PRODUCCION | MERMA].
 * @param cantidad    Cantidad de productos involucrados (debe ser positiva).
 * @param observacion Observación o comentario asociado.
 * @param idPedido    Identificador del pedido relacionado (opcional).
 * @param idProducto  Identificador del producto.
 * @param idBodega    Identificador de la bodega.
 */
public record DatosRegistrarTransaccion(
        @NotNull(message = "campo se requerid [ENTRADA | PRODUCCION | MERMA]")
        TipoTransaccion tipo,

        @NotNull @Positive
        Integer cantidad,

        @NotBlank
        String observacion,

        @Positive
        Long idPedido,

        @NotNull @Positive
        Long idProducto,

        @NotNull @Positive
        Long idBodega
) {}