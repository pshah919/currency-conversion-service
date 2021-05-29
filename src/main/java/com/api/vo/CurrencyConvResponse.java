package com.api.vo;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyConvResponse {

	private Boolean success;
	private String source;
	private String target;
	private BigDecimal value;
	private Date currentDate;

	private ErrorInfo error;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public ErrorInfo getError() {
		return error;
	}

	public void setError(ErrorInfo error) {
		this.error = error;
	}



}
