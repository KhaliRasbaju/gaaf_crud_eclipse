package com.udi.gaaf.bodega;

/**
 * Representa los datos detallados de una bodega.
 * <p>
 * Se utiliza para devolver información resumida o detallada de una bodega
 * en respuestas de API o vistas.
 * </p>
 * 
 * @param id identificador único de la bodega
 * @param nombre nombre de la bodega
 * @param ubicacion ubicación física de la bodega
 */
public record DatosDetalleBodega(
    Long id,
    String nombre,
    String ubicacion
) {
}
