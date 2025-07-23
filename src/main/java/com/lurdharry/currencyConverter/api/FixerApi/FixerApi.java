package com.lurdharry.currencyConverter.api.FixerApi;

import com.lurdharry.currencyConverter.api.ApiService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class FixerApi {

    private  final ApiService apiService;

    public FixerApi(@Qualifier("fixerApiService") ApiService apiService) {
        this.apiService = apiService;
    }

    @Value("${exchange.providers.fixer.api-key}")
    private String apiKey;


    public Mono<FixerLatestRatesResponse> getRates(String fromCurrency) {
        Map<String, String> params = Map.of(
                "access_key", apiKey,
                "base", fromCurrency
        );

        return apiService.makeRequest(
                "/latest",
                HttpMethod.GET,params,
                null,
                FixerLatestRatesResponse.class
        );
    }
}
