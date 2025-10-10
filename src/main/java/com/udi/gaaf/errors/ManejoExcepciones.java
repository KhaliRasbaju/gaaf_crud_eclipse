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


@RestControllerAdvice
public class ManejoExcepciones {

	
	private record DatoErrorValidation(String campo, String error) {
		public DatoErrorValidation(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity TratarNotFoundException(NotFoundException e) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("status", HttpStatus.NOT_FOUND.value());
		json.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(json);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NotRequestBodyException.class)
	public ResponseEntity TratarNotBodyRequest(NotRequestBodyException e) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("status", HttpStatus.BAD_REQUEST.value());
		json.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(json);
	}
	
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity TratarError404(MethodArgumentNotValidException e) {
		var errores = e.getFieldErrors().stream().map(DatoErrorValidation::new).toList();
		return ResponseEntity.badRequest().body(errores);
		
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity TratarBadRequestException(BadRequestException e) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("status", HttpStatus.BAD_REQUEST.value());
		json.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(json);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity TratarErrorValidacion(ValidationException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	
}
