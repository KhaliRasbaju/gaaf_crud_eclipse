package com.udi.gaaf.errors;

/**
 * Excepción personalizada que representa un error de recurso no encontrado (HTTP 404).
 * <p>
 * Se lanza cuando una entidad o recurso solicitado no existe en la base de datos
 * o no puede ser localizado mediante los criterios de búsqueda.
 * </p>
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

    /**
     * Crea una nueva excepción {@code NotFoundException} con un mensaje descriptivo.
     *
     * @param message el mensaje que describe la causa del error.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
