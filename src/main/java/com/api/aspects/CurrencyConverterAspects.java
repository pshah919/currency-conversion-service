package com.api.aspects;

import java.util.Set;

import com.api.vo.CurrencyConvRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.api.exception.SymbolNotFoundException;

@Aspect
@Configuration
public class CurrencyConverterAspects {

	Logger log = LoggerFactory.getLogger(CurrencyConverterAspects.class);

	@Autowired
	private Set<String> loadSymbols;

	@Around("@annotation(loggable)")
	public Object log(ProceedingJoinPoint jp, Loggable loggable) throws Throwable {
		MethodSignature ms = (MethodSignature) jp.getSignature();
		log.info("executing method " + ms.getName());
		Object object = jp.proceed();
		return object;

	}

	@Before("@annotation(com.api.aspects.RateApiRequestValidator)")
	public void validateRequestParams(JoinPoint joinPoint) {
		CurrencyConvRequest currencyConvRequestParams = (CurrencyConvRequest) joinPoint.getArgs()[0];
		String source = currencyConvRequestParams.getSource();
		String target = currencyConvRequestParams.getTarget();
		Double money = currencyConvRequestParams.getMoney();
		if (StringUtils.isEmpty(source) || StringUtils.isEmpty(target)) {
			throw new SymbolNotFoundException(
					"Please enter valid Source and Target Only alphabets no special characters allowed");
		}
		if (!StringUtils.isAlpha(source) || !StringUtils.isAlpha(target)) {
			throw new SymbolNotFoundException(
					"Please enter valid Source and Target Only alphabets no special characters allowed");
		}
		if (money == null || money <= 0) {
			throw new SymbolNotFoundException("Please enter only positive values for money");
		}
		if (!loadSymbols.contains(source) || !loadSymbols.contains(target)) {
			throw new SymbolNotFoundException("Please enter valid Source and Target");
		}

	}

}
