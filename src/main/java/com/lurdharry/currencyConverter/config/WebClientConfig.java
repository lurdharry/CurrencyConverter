package com.lurdharry.currencyConverter.config;


import com.lurdharry.currencyConverter.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ExchangeProviderProperties properties;

    @Bean
    public ApiService openExchangeRatesApiService() {
        String baseUrl = properties.getProviders().get("openexchangerates").getBaseUrl();
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        return new ApiService(webClient);
    }


    @Bean
    public ApiService fixerApiService() {
        String baseUrl = properties.getProviders().get("fixer").getBaseUrl();
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        return new ApiService(webClient);
    }
}
