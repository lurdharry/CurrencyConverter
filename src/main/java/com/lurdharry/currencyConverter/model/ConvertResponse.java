package com.lurdharry.currencyConverter.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ConvertResponse(
        String toCurrency,
        BigDecimal rate,
        BigDecimal convertedAmount,
        Money fromData
) {
}
