package com.api.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SymbolResponse {

	private String success;

	private Map<String, String> symbols;

	private ErrorInfo error;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Map<String, String> getSymbols() {
		return symbols;
	}

	public void setSymbols(Map<String, String> symbols) {
		this.symbols = symbols;
	}

	public ErrorInfo getError() {
		return error;
	}

	public void setError(ErrorInfo error) {
		this.error = error;
	}

}
