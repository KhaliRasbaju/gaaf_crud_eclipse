package com.udi.gaaf.detalle_pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

/**
 * Datos necesarios para registrar o editar un detalle de pedido.
 * Incluye las validaciones para cada campo.
 * 
 * @param humedad      Nivel de humedad del producto (mayor a 0).
 * @param estadoCacao  Estado del cacao en porcentaje (mayor a 0).
 * @param fermentacion Porcentaje de fermentación (mayor a 0).
 * @param cantidad     Cantidad de unidades (mayor a 0).
 * @param peso         Peso total del producto (mayor a 0).
 * @param observacion  Observación opcional del detalle (puede ser nula).
 * @param idProducto   Identificador del producto asociado.
 */
public record DatosRegistrarDetallePedido(
    @Positive @NotNull Float humedad,
    @Positive @NotNull Float estadoCacao,
    @Positive @NotNull Float fermentacion,
    @Positive @NotNull Integer cantidad,
    @Positive @NotNull Float peso,
    @Null String observacion,
    @Positive @NotNull Long idProducto
) {}
