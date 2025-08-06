package com.lurdharry.currencyConverter.service;

import com.lurdharry.currencyConverter.model.ConvertResponse;
import com.lurdharry.currencyConverter.adapter.CurrencyAdapter;
import com.lurdharry.currencyConverter.exceptions.CurrencyConversionException;
import com.lurdharry.currencyConverter.exceptions.ProviderNotAvailableException;
import com.lurdharry.currencyConverter.model.Money;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {
    private final List<CurrencyAdapter> adapters;

    @PostConstruct
    public void logAdapters() {
        log.info("Loaded {} currency adapters:", adapters.size());
        adapters.forEach(adapter -> log.info("- {}", adapter.getProviderName()));
    }

    public Mono<List<String>> getProviderNames() {
        return Mono.just(adapters.stream()
                .map(CurrencyAdapter::getProviderName)
                .toList());
    }

    public Mono<ConvertResponse> convertMoney(Money from, String toCurrency){

        return tryAdapter(adapter -> adapter.convertCurrency(from,toCurrency));
    }


    private <T> Mono<T> tryAdapter(Function<CurrencyAdapter, Mono<T>> adapterCall){
        return Flux.fromIterable(adapters)
                .concatMap((adapter)->{
                    log.info("Trying adapter: {}", adapter.getProviderName());
                    return adapterCall.apply(adapter)
                            .doOnSuccess(result -> log.info("Success with: {}", adapter.getProviderName()))
                            .onErrorResume(error ->{
                                log.warn("Provider {} failed: {}", adapter.getProviderName(), error.getMessage(),error);
                                return Mono.empty(); // Return empty to try next adapter
                            });
                })
                .next()
                .switchIfEmpty(Mono.error(new ProviderNotAvailableException("All providers failed.")));
    }
}