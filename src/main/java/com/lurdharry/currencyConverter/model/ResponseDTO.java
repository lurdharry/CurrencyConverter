package com.lurdharry.currencyConverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Generic response object for API endpoints
 * @param status HTTP status code or custom status
 * @param message Response message
 * @param data Response payload
 * @param <T> Type of the response data
 */
public record ResponseDTO<T>(
        Integer status,
        String message,
        T data
) {
}
