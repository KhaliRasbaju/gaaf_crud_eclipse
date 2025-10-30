package com.udi.gaaf.pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.udi.gaaf.detalle_pedido.DatosDetalleDetallePedido;
import com.udi.gaaf.medio_pago.DatosDetalleMedioPago;

/**
 * DTO que representa los detalles de un pedido.
 *
 * @param id             Identificador único del pedido.
 * @param nitProveedor   NIT del proveedor asociado al pedido.
 * @param valor          Valor total del pedido.
 * @param fechaPedido    Fecha en la que se realizó el pedido.
 * @param recibido       Indica si el pedido fue recibido.
 * @param fechaEntrega   Fecha en la que se entregó el pedido.
 * @param medioPago      Medio de pago utilizado.
 * @param detallePedido  Lista de detalles del pedido (productos y cantidades).
 */
public record DatosDetallePedido(
    Long id,
    Long nitProveedor,
    Double valor,
    LocalDateTime fechaPedido,
    Boolean recibido,
    LocalDateTime fechaEntrega,
    DatosDetalleMedioPago medioPago,
    List<DatosDetalleDetallePedido> detallePedido
) {}
