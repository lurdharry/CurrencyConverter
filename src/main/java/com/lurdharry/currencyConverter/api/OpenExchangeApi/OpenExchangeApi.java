package com.lurdharry.currencyConverter.api.OpenExchangeApi;

import com.lurdharry.currencyConverter.api.ApiService;
import com.lurdharry.currencyConverter.model.Money;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

public class OpenExchangeApi {
    private  final ApiService apiService;

    private String apiKey = "apikey";

    public OpenExchangeApi(){
        WebClient.Builder builder = WebClient.builder()
                .baseUrl("https://openexchangerates.org/api");
        this.apiService = new ApiService(builder);
    }

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
