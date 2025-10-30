package com.udi.gaaf.errors;

/**
 * Excepción personalizada que representa un error de solicitud incorrecta (HTTP 400).
 * <p>
 * Se lanza cuando los datos enviados por el cliente no cumplen con las reglas
 * esperadas o cuando la solicitud no puede procesarse correctamente debido a un error del usuario.
 * </p>
 */
@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

    /**
     * Crea una nueva excepción {@code BadRequestException} con un mensaje descriptivo.
     *
     * @param message el mensaje que describe la causa del error.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
