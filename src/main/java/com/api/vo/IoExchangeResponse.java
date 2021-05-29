package com.api.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class IoExchangeResponse {

	private Boolean success;

	private Long timestamp;

	private String base;
	
	private ErrorInfo error;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String date;

	private Map<String, Float> rates;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Float> getRates() {
		return rates;
	}

	public void setRates(Map<String, Float> rates) {
		this.rates = rates;
	}

	public ErrorInfo getError() {
		return error;
	}

	public void setError(ErrorInfo error) {
		this.error = error;
	}
	
	

}