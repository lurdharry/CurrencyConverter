package com.lurdharry.currencyConverter.controller;

import com.lurdharry.currencyConverter.adapter.CurrencyAdapter;
import com.lurdharry.currencyConverter.model.Money;
import com.lurdharry.currencyConverter.model.ResponseDTO;
import com.lurdharry.currencyConverter.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;
    private final List<CurrencyAdapter> adapters;

    @PostMapping("/convert")
    public Mono<ResponseDTO> convert (@Valid @RequestBody ConvertRequest request){
        Money fromMoney = new Money(request.amount(), request.from().toUpperCase());

       return currencyService.convertMoney(fromMoney,request.to().toUpperCase()).map(result->
               ResponseDTO.builder()
                       .data(result)
                       .message("Currency converted successfully")
                       .status(HttpStatus.OK.value())
                       .build()
       );
    }

    @GetMapping("/providers")
    public List<String> getProviders() {
        return adapters.stream()
                .map(CurrencyAdapter::getProviderName)
                .collect(Collectors.toList());
    }
}
