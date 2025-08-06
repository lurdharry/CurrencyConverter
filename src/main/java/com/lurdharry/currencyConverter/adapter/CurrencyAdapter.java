package com.lurdharry.currencyConverter.adapter;

import com.lurdharry.currencyConverter.model.ConvertResponse;
import com.lurdharry.currencyConverter.model.Money;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Mono;

public interface CurrencyAdapter {
    Mono<ConvertResponse> convertCurrency(@NotNull Money money, @NotBlank String toCurrency);
    String getProviderName();
}
