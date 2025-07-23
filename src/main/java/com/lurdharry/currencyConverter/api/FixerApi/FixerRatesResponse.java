package com.lurdharry.currencyConverter.api.FixerApi;

import java.math.BigDecimal;
import java.util.Map;

public record FixerRatesResponse(
        boolean success,
        long timestamp,
        String base,
        String date,
        Map<String, BigDecimal> rates
) {
}
