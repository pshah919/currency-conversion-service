package com.api.vo;

import org.apache.commons.lang3.StringUtils;

public class CurrencyConvRequest {

	private String source;
	private String target;
	private Double money;

	public String getSource() {
		return (StringUtils.isEmpty(source)) ? source : source.toUpperCase();
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return (StringUtils.isEmpty(target)) ? target : target.toUpperCase();
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Request [source=" + source + ", target=" + target + ", money=" + money + "]";
	}

}
