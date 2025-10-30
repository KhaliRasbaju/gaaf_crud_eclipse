package com.udi.gaaf.errors;

/**
 * Excepción personalizada que representa un error de cuerpo de solicitud inválido o ausente.
 * <p>
 * Se lanza cuando el cuerpo del request es nulo, vacío o no cumple con el formato esperado.
 * </p>
 */
@SuppressWarnings("serial")
public class NotRequestBodyException extends RuntimeException {

    /**
     * Crea una nueva excepción {@code NotRequestBodyException} con un mensaje descriptivo.
     *
     * @param message el mensaje que describe la causa del error.
     */
    public NotRequestBodyException(String message) {
        super(message);
    }
}
