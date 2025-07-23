package com.lurdharry.currencyConverter.adapter.impl;

import com.lurdharry.currencyConverter.adapter.CurrencyAdapter;
import com.lurdharry.currencyConverter.api.FixerApi.FixerApi;
import com.lurdharry.currencyConverter.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class FixerApiAdapter implements CurrencyAdapter {
    private final FixerApi fixerApi;

    @Override
    public Mono<BigDecimal> convertCurrency(Money money, String toCurrency) {
        return fixerApi.convertCurrency(money,toCurrency).map(Money::value);
    }

    @Override
    public String getProviderName() {
        return "Fixer";
    }
}
