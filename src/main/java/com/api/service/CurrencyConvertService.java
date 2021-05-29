package com.api.service;

import java.util.Map;

import com.api.vo.CurrencyConvRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.exception.ApiFailureException;
import com.api.exception.ApplicationException;
import com.api.utils.AppUtils;
import com.api.vo.CurrencyConvResponse;
import com.api.vo.IoExchangeResponse;

@Service
public class CurrencyConvertService {

	@Value("${rate.api}")
	private String rateApi;

	@Value("${access.key}")
	private String accessKey;

	@Autowired
	RestTemplate restTemplate;

	public CurrencyConvResponse getRateForTarget(CurrencyConvRequest params) {
		CurrencyConvResponse currencyConvResponse = null;
		try {
			if(params.getSource().equals(params.getTarget())) {
				return currencyConvResponse = AppUtils.setResponse(params, params.getMoney());
			}
			IoExchangeResponse responseForApi = restTemplate.getForObject(rateApi + accessKey, IoExchangeResponse.class);
			if (responseForApi != null && responseForApi.getSuccess()) {
				if (responseForApi.getRates() != null) {
					double convertedCurrency = this.calculateRatesUsingSourceAndTarget(params, responseForApi);
					currencyConvResponse = AppUtils.setResponse(params, convertedCurrency);
				}
			} else {
				throw new ApiFailureException(responseForApi.getError().getInfo(), responseForApi.getError().getCode());
			}
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}

		return currencyConvResponse;

	}

	private double calculateRatesUsingSourceAndTarget(CurrencyConvRequest params, IoExchangeResponse response) {
		double convertedCurrency = 0.0;
		String source = params.getSource();
		String target = params.getTarget();
		double money = params.getMoney();
		if (source.equals(response.getBase())) {
			if (response.getRates().get(target) != null)
				convertedCurrency = response.getRates().get(target) * money;
		} else {
			Map ratesMap = response.getRates();
			Float sourceRate = (Float) ratesMap.get(source);
			Float targetRate = (Float) ratesMap.get(target);
			if (sourceRate != null && targetRate != null) {
				Float rateForEuro = 1 / sourceRate;
				convertedCurrency = rateForEuro * targetRate * money;
			}

		}
		return convertedCurrency;

	}

}
