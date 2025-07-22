package com.lurdharry.currencyConverter.adapter;

import com.lurdharry.currencyConverter.service.Money;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CurrencyAdapter {
    Mono<BigDecimal> convertCurrency(Money money, String toCurrency);
    String getProviderName();
}
