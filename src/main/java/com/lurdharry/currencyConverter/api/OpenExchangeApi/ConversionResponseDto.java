package com.lurdharry.currencyConverter.api.OpenExchangeApi;

import java.math.BigDecimal;

public record ConversionResponseDto(
        String disclaimer,
        String license,
        Request request,
        Meta    meta,
        BigDecimal response
) {
}

record Request(
        String query,
        double amount,
        String from,
        String to
) {}

record Meta(
        long   timestamp,
        double rate
) {}
