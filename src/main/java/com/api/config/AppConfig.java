package com.api.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.api.exception.ApiFailureException;
import com.api.exception.ApplicationException;
import com.api.vo.SymbolResponse;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppConfig {


    @Value("${symbols.api}")
    private String rateApi;

    @Value("${access.key}")
    private String accessKey;
    @Autowired
    RestTemplate restTemplate;
    @Bean
    public Set<String> loadSymbols() {
        Set<String> symbols = new HashSet<>();
        try {
            SymbolResponse result = restTemplate.getForObject(rateApi + accessKey, SymbolResponse.class);
            if (result != null && result.getSuccess().equals("true"))
                symbols.addAll(result.getSymbols().keySet());
            else
                throw new ApiFailureException(result.getError().getInfo(), result.getError().getCode());
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
        return symbols;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
