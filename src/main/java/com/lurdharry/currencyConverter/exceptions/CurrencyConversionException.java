package com.lurdharry.currencyConverter.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CurrencyConversionException extends RuntimeException {
    private final String msg;
}
