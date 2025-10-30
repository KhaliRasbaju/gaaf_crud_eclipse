package com.udi.gaaf.common;

/**
 * Representa una respuesta estándar utilizada para comunicar el resultado
 * de una operación dentro del sistema.
 * <p>
 * Este modelo es comúnmente utilizado en operaciones de creación, actualización
 * o eliminación para retornar el estado de la transacción junto con un mensaje descriptivo.
 * </p>
 *
 * @param status código de estado HTTP o código de resultado
 * @param message mensaje que describe el resultado de la operación
 */
public record DatosDetalleResponse(
    Integer status,
    String message
) {}
