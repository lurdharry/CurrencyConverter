package com.lurdharry.currencyConverter.service;

public interface CurrencyService {
    double getExchangeRate(Money money, String toCurrency);
}
