package com.udi.gaaf.detalle_pedido;

/**
 * Representa los datos de detalle de un pedido,
 * utilizados para la visualización o respuesta de la API.
 * 
 * @param humedad        Nivel de humedad del producto.
 * @param estadoCacao    Estado del cacao medido en porcentaje.
 * @param fermentacion   Porcentaje de fermentación del cacao.
 * @param cantidad       Cantidad de productos en el detalle.
 * @param peso           Peso total del producto.
 * @param producto       Nombre del producto asociado.
 */
public record DatosDetalleDetallePedido(
    Float humedad,
    Float estadoCacao,
    Float fermentacion,
    Integer cantidad,
    Float peso,
    String producto
) {}
