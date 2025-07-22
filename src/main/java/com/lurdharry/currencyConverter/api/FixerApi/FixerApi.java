package com.lurdharry.currencyConverter.api.FixerApi;

import com.lurdharry.currencyConverter.api.ApiService;
import com.lurdharry.currencyConverter.model.Money;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class FixerApi {

    private  final ApiService apiService;

    private String apiKey = "apikey";

    public FixerApi(){
        WebClient.Builder builder = WebClient.builder()
                .baseUrl("http://data.fixer.io/api");
        this.apiService = new ApiService(builder);
    }

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
