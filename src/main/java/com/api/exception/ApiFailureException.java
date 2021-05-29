package com.api.exception;

public class ApiFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer code;

	public ApiFailureException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public ApiFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
