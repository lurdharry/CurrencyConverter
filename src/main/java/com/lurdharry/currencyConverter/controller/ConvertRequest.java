package com.lurdharry.currencyConverter.controller;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ConvertRequest(

        @NotNull(message="from currency is required")
        String from,

        @NotNull(message="to currency is required")
        String to,

        @NotNull(message="amount is required")
        @Positive(message = "Quantity must be a positive number")
        BigDecimal amount
) {
}
