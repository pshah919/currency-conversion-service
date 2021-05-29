package com.api.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.api.vo.CurrencyConvRequest;
import com.api.vo.CurrencyConvResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.api.exception.ApplicationException;
import com.api.vo.IoExchangeResponse;

@RunWith(MockitoJUnitRunner.class)
public class TestCurrencyConvertService {

    @Value("${rate.api}")
    private String rateApi;

    @Value("${access.key}")
    private String accessKey;

    @InjectMocks
    private CurrencyConvertService service;

    @Mock
    private CurrencyConvRequest currencyConvRequest;

    @Mock
    private RestTemplate template;

    @Mock
    private IoExchangeResponse ioExchangeResponse;

    @Mock
    private Map<String, Float> rate;

    @Before
    public void setUp() {
        Mockito.when(template.getForObject(rateApi + accessKey, IoExchangeResponse.class)).thenReturn(responseApi());

    }

    @Test
    public void testGetRateForTarget() {
        Assert.assertNotNull(service.getRateForTarget(request()));
    }

    @Test(expected = ApplicationException.class)
    public void testGetRateForTargetCase2() {
        Assert.assertNotNull(service.getRateForTarget(requestCase2()));
    }

    private IoExchangeResponse responseApi() {
        IoExchangeResponse response = new IoExchangeResponse();
        response.setSuccess(true);
        response.setRates(setDummyMap());
        return response;
    }

    private CurrencyConvRequest request() {
        CurrencyConvRequest currencyConvRequest = new CurrencyConvRequest();
        currencyConvRequest.setSource("USD");
        currencyConvRequest.setTarget("INR");
        currencyConvRequest.setMoney(20.0);
        return currencyConvRequest;
    }

    private CurrencyConvRequest requestCase2() {
        CurrencyConvRequest currencyConvRequest = new CurrencyConvRequest();
        currencyConvRequest.setSource("USD");
        currencyConvRequest.setTarget("abc");
        currencyConvRequest.setMoney(20.0);
        return currencyConvRequest;
    }

    private Map setDummyMap() {
        Map<String, Float> map = new HashMap<>();
        map.put("USD", 1.2f);
        map.put("INR", 1.6f);
        //map.put("EUR", 1.2f);
        return map;
    }


    @Test
    public void testCalculateRatesUsingSourceAndTarget() {
        CurrencyConvRequest currencyConvRequest = new CurrencyConvRequest();
        currencyConvRequest.setSource("USD");
        currencyConvRequest.setTarget("INR");
        currencyConvRequest.setMoney(20.0);

        IoExchangeResponse response = new IoExchangeResponse();
        response.setSuccess(true);
        response.setRates(setDummyMap());
        CurrencyConvResponse serviceCurrencyConvResponse = service.getRateForTarget(currencyConvRequest);
        BigDecimal actual = serviceCurrencyConvResponse.getValue();
        BigDecimal expected= new BigDecimal(26.66);
		Assert.assertEquals( actual,expected);

    }

}
