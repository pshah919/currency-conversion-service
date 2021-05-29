package com.api.controller;

import com.api.utils.AppUtils;
import com.api.vo.CurrencyConvResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.aspects.Loggable;
import com.api.aspects.RateApiRequestValidator;
import com.api.service.CurrencyConvertService;
import com.api.vo.CurrencyConvRequest;

@RestController
@RequestMapping("/api")
public class CurrencyConversionController {
    Logger log = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyConvertService currencyService;

    @Loggable
    @RateApiRequestValidator
    @GetMapping("/convertCurrency")

    public ResponseEntity<CurrencyConvResponse> convertCurrency(CurrencyConvRequest currencyConvRequest) {
        CurrencyConvResponse currencyConvResponse = currencyService.getRateForTarget(currencyConvRequest);
        if (currencyConvRequest != null) {
            return ResponseEntity.ok(currencyConvResponse);
        }
        currencyConvResponse= AppUtils.setResponseForError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Service not available at this time , Please try later");
        return ResponseEntity.ok(currencyConvResponse);

    }
}
