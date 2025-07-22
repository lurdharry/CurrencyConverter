package com.lurdharry.currencyConverter.adapter;

import com.lurdharry.currencyConverter.model.Money;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CurrencyAdapter {
    Mono<BigDecimal> convertCurrency(Money money, String toCurrency);
    String getProviderName();
}
