package com.udi.gaaf.transaccion_inventario;

/**
 * Record que representa los datos detallados de una transacción de inventario.
 * Se utiliza para mostrar información completa al usuario.
 *
 * @param id          Identificador único de la transacción.
 * @param tipo        Tipo de transacción [ENTRADA | PRODUCCION | MERMA].
 * @param cantidad    Cantidad de unidades afectadas en la transacción.
 * @param observacion Comentario o nota asociada a la transacción.
 * @param idPedido    Identificador del pedido relacionado (si aplica).
 * @param producto    Nombre del producto involucrado.
 * @param bodega      Nombre de la bodega donde se realizó la transacción.
 */
public record DatosDetalleTransaccion(
        Long id,
        TipoTransaccion tipo,
        Integer cantidad,
        String observacion,
        Long idPedido,
        String producto,
        String bodega
) {}
