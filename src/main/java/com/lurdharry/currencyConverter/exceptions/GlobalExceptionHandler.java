package com.lurdharry.currencyConverter.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyConversionException.class)
    public ResponseEntity<Map<String,Object>> handleCurrencyConversionException(
            CurrencyConversionException e
    ){

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Currency Conversion Failed");
        error.put("timeStamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(ProviderNotAvailableException.class)
    public ResponseEntity<Map<String, Object>> handleProviderException(ProviderNotAvailableException e) {
        log.error("Provider error: {}", e.getMsg());

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Service Unavailable");
        error.put("message", e.getMsg());
        error.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", "An unexpected error occurred");
        error.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
