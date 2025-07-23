package com.lurdharry.currencyConverter.api.FixerApi;

import com.lurdharry.currencyConverter.api.ApiService;
import com.lurdharry.currencyConverter.model.Money;
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


    public Mono<Money> convertCurrency(Money money, String toCurrency) {
        Map<String, String> params = Map.of(
                "access_key", apiKey,
                "from", money.currency(),
                "to", toCurrency,
                "amount", String.valueOf(money.value())
        );

        return apiService.makeRequest("/convert", HttpMethod.GET,params,null,ConversionResponseDto.class)
                .map(res-> new Money(res.result(),toCurrency)
                );
    }
}
