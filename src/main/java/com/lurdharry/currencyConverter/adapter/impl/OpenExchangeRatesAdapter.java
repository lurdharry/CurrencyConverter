package com.lurdharry.currencyConverter.adapter.impl;

import com.lurdharry.currencyConverter.exceptions.CurrencyConversionException;
import com.lurdharry.currencyConverter.model.ConvertResponse;
import com.lurdharry.currencyConverter.adapter.CurrencyAdapter;
import com.lurdharry.currencyConverter.api.OpenExchangeApi.OpenExchangeApi;
import com.lurdharry.currencyConverter.model.Money;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
@Slf4j
public class OpenExchangeRatesAdapter implements CurrencyAdapter {
    private final OpenExchangeApi openExchangeApi;

    @Override
    public Mono<ConvertResponse> convertCurrency(Money money, String toCurrency) {

        return openExchangeApi.convertCurrency(money.currency())
                .flatMap(response ->{
                    BigDecimal rate = response.rates().get(toCurrency);
                    if (rate == null) {
                        throw new CurrencyConversionException("Rate for " + toCurrency + " not found in OpenExchange response.");
                    }
                    log.info("rate gotten {} ", rate);

                    var res = ConvertResponse.builder()
                            .convertedAmount(money.value().multiply(rate))
                            .rate(rate)
                            .fromData(money)
                            .toCurrency(toCurrency)
                            .build();

                    return Mono.just(res);
                });
    }

    @Override
    public String getProviderName() {
        return "OpenExchangeRates";
    }
}
