package com.lurdharry.currencyConverter.adapter.impl;

import com.lurdharry.currencyConverter.model.ConvertResponse;
import com.lurdharry.currencyConverter.adapter.CurrencyAdapter;
import com.lurdharry.currencyConverter.api.OpenExchangeApi.OpenExchangeApi;
import com.lurdharry.currencyConverter.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class OpenExchangeRatesAdapter implements CurrencyAdapter {
    private final OpenExchangeApi openExchangeApi;

    @Override
    public Mono<ConvertResponse> convertCurrency(Money money, String toCurrency) {
        return null;
    }

    @Override
    public String getProviderName() {
        return "OpenExchangeRates";
    }
}
