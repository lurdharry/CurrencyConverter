package com.lurdharry.currencyConverter.adapter.impl;

import com.lurdharry.currencyConverter.adapter.CurrencyAdapter;
import com.lurdharry.currencyConverter.api.OpenExchangeApi.OpenExchangeApi;
import com.lurdharry.currencyConverter.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class OpenExchangeRatesAdapter implements CurrencyAdapter {
    private final OpenExchangeApi openExchangeApi;

    @Override
    public Mono<BigDecimal> convertCurrency(Money money, String toCurrency) {
        return openExchangeApi.convertCurrency(money,toCurrency).map(Money::value);
    }

    @Override
    public String getProviderName() {
        return "OpenExchangeRates";
    }
}
