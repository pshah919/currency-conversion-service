package com.api.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.api.exception.ApplicationException;
import com.api.vo.CurrencyConvRequest;
import com.api.vo.CurrencyConvResponse;
import com.api.vo.ErrorInfo;

public class AppUtils {

	public static CurrencyConvResponse setResponseForError(int status, String message) {
		CurrencyConvResponse currencyConvResponse = new CurrencyConvResponse();
		currencyConvResponse.setSuccess(false);
		currencyConvResponse.setCurrentDate(new Date());
		ErrorInfo error = new ErrorInfo();
		error.setCode(status);
		error.setInfo(message);
		currencyConvResponse.setError(error);
		return currencyConvResponse;
	}
	
	public static CurrencyConvResponse setResponse(CurrencyConvRequest params, double convertedCurrency) {
		if(convertedCurrency==0.0) {
			throw new ApplicationException("Error encountered while converting currency");
		}
		CurrencyConvResponse currencyConvResponse = new CurrencyConvResponse();
		currencyConvResponse.setSource(params.getSource());
		currencyConvResponse.setTarget(params.getTarget());
		currencyConvResponse.setSuccess(true);
		//rounding off to 2 decimal places
		 BigDecimal bd = new BigDecimal(convertedCurrency).setScale(2, RoundingMode.HALF_UP);
		currencyConvResponse.setValue(bd);
		currencyConvResponse.setCurrentDate(new Date());
		return currencyConvResponse;
	}
}
