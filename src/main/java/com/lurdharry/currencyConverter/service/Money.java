package com.lurdharry.currencyConverter.service;


import java.math.BigDecimal;

public record Money(BigDecimal value, String currency) {
}
