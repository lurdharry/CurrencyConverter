package com.lurdharry.currencyConverter.api.OpenExchangeApi;

import com.lurdharry.currencyConverter.api.ApiService;
import com.lurdharry.currencyConverter.model.Money;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class OpenExchangeApi {
    private  final ApiService apiService;

    public OpenExchangeApi(@Qualifier("openExchangeRatesApiService") ApiService apiService) {
        this.apiService = apiService;
    }

    @Value("${exchange.providers.openexchangerates.api-key}")
    private String apiKey;

    public Mono<Money> convertCurrency(Money money, String toCurrency) {
        Map<String, String> params = Map.of("app_id", apiKey);
        var endpoint = "/convert/" + money.value() +"/" + money.currency() + "/" + toCurrency;

        return apiService.makeRequest(endpoint, HttpMethod.GET,params,null,ConversionResponseDto.class).map(
                response -> {
                    double rate = response.meta().rate();
                    BigDecimal newValue = response.response();
                    return new Money(newValue,toCurrency);
                }
        );
    }
}
