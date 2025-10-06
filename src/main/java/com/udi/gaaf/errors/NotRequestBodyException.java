package com.udi.gaaf.errors;

@SuppressWarnings("serial")
public class NotRequestBodyException extends RuntimeException {
	public NotRequestBodyException(String message) {
		super(message);
	}
}
