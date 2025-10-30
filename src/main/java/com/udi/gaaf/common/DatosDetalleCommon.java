package com.udi.gaaf.common;

/**
 * Representa un modelo genérico para el detalle de una entidad común.
 * <p>
 * Este registro se utiliza en respuestas donde solo se requiere
 * mostrar información básica de identificación, como el identificador
 * y el nombre del elemento.
 * </p>
 *
 * @param id identificador único del elemento
 * @param nombre nombre del elemento
 */
public record DatosDetalleCommon(
    Long id,
    String nombre
) {}
