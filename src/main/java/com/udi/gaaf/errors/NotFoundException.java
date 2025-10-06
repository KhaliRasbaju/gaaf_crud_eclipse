package com.udi.gaaf.errors;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
