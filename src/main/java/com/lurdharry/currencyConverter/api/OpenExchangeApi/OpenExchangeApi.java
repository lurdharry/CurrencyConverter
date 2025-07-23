package com.lurdharry.currencyConverter.api.OpenExchangeApi;

import com.lurdharry.currencyConverter.api.ApiService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class OpenExchangeApi {
    private  final ApiService apiService;

    public OpenExchangeApi(@Qualifier("openExchangeRatesApiService") ApiService apiService) {
        this.apiService = apiService;
    }

    @Value("${exchange.providers.openexchangerates.api-key}")
    private String apiKey;

    public Mono<OpenExchangeRatesResponse> convertCurrency( String fromCurrency) {
        Map<String, String> params = Map.of(
                "app_id", apiKey
//                "base", fromCurrency
        );

        return apiService.makeRequest(
                "/latest.json",
                HttpMethod.GET,
                params,
                null,
                OpenExchangeRatesResponse.class
        );
    }
}
