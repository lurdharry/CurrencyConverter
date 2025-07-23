package com.lurdharry.currencyConverter.adapter;

import com.lurdharry.currencyConverter.model.ConvertResponse;
import com.lurdharry.currencyConverter.model.Money;
import reactor.core.publisher.Mono;

public interface CurrencyAdapter {
    Mono<ConvertResponse> convertCurrency(Money money, String toCurrency);
    String getProviderName();
}
