package com.udi.gaaf.errors;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}
