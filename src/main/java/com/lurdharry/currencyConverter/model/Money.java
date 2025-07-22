package com.lurdharry.currencyConverter.model;


import java.math.BigDecimal;

public record Money(BigDecimal value, String currency) {
}
