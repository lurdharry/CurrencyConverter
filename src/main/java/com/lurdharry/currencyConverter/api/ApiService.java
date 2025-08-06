package com.lurdharry.currencyConverter.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class ApiService {

    private final WebClient webClient;

    public <T> Mono<T> makeRequest(
            String endpoint,
            HttpMethod method,
            Map<String, String> queryParams,
            Object requestBody,
            Class<T> responseType
    ){
        return webClient.method(method)
                .uri(uriBuilder ->{
                    uriBuilder.path(endpoint);
                    if (queryParams != null && !queryParams.isEmpty()) {
                        queryParams.forEach(uriBuilder::queryParam);
                    }
                    return  uriBuilder.build();
                })
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody == null? "" : requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .timeout(Duration.ofSeconds(10))
                .doOnError(e -> log.error("Error making API request: {}", e.getMessage()));
    }
}
