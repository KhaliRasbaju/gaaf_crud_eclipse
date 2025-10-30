package com.udi.gaaf.errors;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ValidationException;

/**
 * Clase centralizada para el manejo global de excepciones dentro de la aplicación.
 * 
 * <p>Utiliza {@link RestControllerAdvice} para interceptar excepciones
 * y devolver respuestas JSON coherentes con los códigos HTTP apropiados.</p>
 */
@RestControllerAdvice
public class ManejoExcepciones {

    /**
     * Registro que representa un error de validación con el nombre del campo y el mensaje correspondiente.
     *
     * @param campo nombre del campo que causó el error.
     * @param error mensaje de error de validación asociado al campo.
     */
    private record DatoErrorValidation(String campo, String error) {
        public DatoErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    /**
     * Maneja las excepciones {@link NotFoundException}.
     *
     * @param e la excepción lanzada cuando no se encuentra un recurso.
     * @return una respuesta HTTP 404 con el mensaje del error.
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity TratarNotFoundException(NotFoundException e) {
        Map<String, Object> json = new HashMap<>();
        json.put("status", HttpStatus.NOT_FOUND.value());
        json.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(json);
    }

    /**
     * Maneja las excepciones {@link NotRequestBodyException}.
     *
     * @param e la excepción lanzada cuando el cuerpo de la solicitud no es válido.
     * @return una respuesta HTTP 400 con un mensaje descriptivo.
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(NotRequestBodyException.class)
    public ResponseEntity TratarNotBodyRequest(NotRequestBodyException e) {
        Map<String, Object> json = new HashMap<>();
        json.put("status", HttpStatus.BAD_REQUEST.value());
        json.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(json);
    }

    /**
     * Maneja los errores de validación de argumentos en los controladores.
     *
     * @param e la excepción {@link MethodArgumentNotValidException} generada por validaciones de campos.
     * @return una lista de errores con los campos y mensajes correspondientes.
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity TratarError404(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatoErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    /**
     * Maneja las excepciones {@link BadRequestException}.
     *
     * @param e la excepción lanzada cuando la solicitud no es válida.
     * @return una respuesta HTTP 400 con el mensaje del error.
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity TratarBadRequestException(BadRequestException e) {
        Map<String, Object> json = new HashMap<>();
        json.put("status", HttpStatus.BAD_REQUEST.value());
        json.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(json);
    }

    /**
     * Maneja errores de validación general lanzados por Jakarta Validation.
     *
     * @param e la excepción {@link ValidationException} capturada.
     * @return una respuesta HTTP 400 con el mensaje de validación.
     */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity TratarErrorValidacion(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
