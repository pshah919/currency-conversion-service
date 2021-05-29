package com.api.exception;

public class SymbolNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SymbolNotFoundException(String message) {
		super(message);
	}

	public SymbolNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
