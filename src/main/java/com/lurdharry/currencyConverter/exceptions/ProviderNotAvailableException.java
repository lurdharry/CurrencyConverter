package com.lurdharry.currencyConverter.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProviderNotAvailableException extends RuntimeException {
    private final String msg;
}
