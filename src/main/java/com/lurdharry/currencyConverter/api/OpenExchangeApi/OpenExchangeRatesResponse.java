package com.lurdharry.currencyConverter.api.OpenExchangeApi;

import java.math.BigDecimal;
import java.util.Map;

public record OpenExchangeRatesResponse(
        long timestamp,
        String base,
        Map<String, BigDecimal> rates
) {
}
