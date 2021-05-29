package com.api.exception;

import com.api.vo.CurrencyConvResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.utils.AppUtils;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(SymbolNotFoundException.class)
	public ResponseEntity<CurrencyConvResponse> symbolHandler(SymbolNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(AppUtils.setResponseForError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));

	}

	@ExceptionHandler(ApiFailureException.class)
	public ResponseEntity<CurrencyConvResponse> apiHandler(ApiFailureException ex) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(AppUtils
				.setResponseForError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Please contact System Administrator"));

	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<CurrencyConvResponse> appHandler(ApplicationException ex) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(AppUtils.setResponseForError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));

	}

}
