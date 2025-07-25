package com.lurdharry.currencyConverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseDTO(
        Integer status,
        String message,
        Object data
) {
}
