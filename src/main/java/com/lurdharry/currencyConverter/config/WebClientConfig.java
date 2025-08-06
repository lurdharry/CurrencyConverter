package com.lurdharry.currencyConverter.config;

import com.lurdharry.currencyConverter.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ExchangeProviderProperties properties;

    @Bean
    @Qualifier("openExchangeRates")
    public ApiService openExchangeRatesApiService() {
        return new ApiService(createWebClient("openexchangerates"));
    }

    @Bean
    @Qualifier("fixer")
    public ApiService fixerApiService() {
        return new ApiService(createWebClient("fixer"));
    }

    private WebClient createWebClient(String providerKey) {
        String baseUrl = properties.getProviders()
                .get(providerKey)
                .getBaseUrl();
        return WebClient.builder().baseUrl(baseUrl)
                .build();
    }
}