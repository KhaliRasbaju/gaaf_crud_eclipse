package com.udi.gaaf.pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.udi.gaaf.detalle_pedido.DatosRegistrarDetallePedido;
import com.udi.gaaf.medio_pago.DatosRegistrarMedioPago;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO para registrar un nuevo pedido.
 *
 * @param fechaPedido Fecha en la que se genera el pedido.
 * @param valor       Valor total del pedido.
 * @param nitProveedor NIT del proveedor asociado.
 * @param medioPago   Datos del medio de pago utilizado.
 * @param detalle     Lista de detalles del pedido (productos y cantidades).
 */
public record DatosRegistrarPedido(
    @NotNull
    LocalDateTime fechaPedido,

    @Positive
    @NotNull
    Double valor,

    @Positive
    @NotNull
    Long nitProveedor,

    @NotNull
    @Valid
    DatosRegistrarMedioPago medioPago,

    @NotNull
    @Valid
    List<DatosRegistrarDetallePedido> detalle
) {}
