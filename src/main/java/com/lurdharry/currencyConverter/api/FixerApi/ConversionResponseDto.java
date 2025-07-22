package com.lurdharry.currencyConverter.api.FixerApi;

import java.math.BigDecimal;


public record ConversionResponseDto(
        boolean success,
        String date,
        Query   query,
        Info info,
        BigDecimal result
) {
}

record Query(
        double amount,
        String from,
        String to
) {}

record Info(
        long   timestamp,
        double rate
) {}
