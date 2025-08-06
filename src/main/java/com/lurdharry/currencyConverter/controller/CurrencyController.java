package com.lurdharry.currencyConverter.controller;

import com.lurdharry.currencyConverter.model.Money;
import com.lurdharry.currencyConverter.model.ResponseDTO;
import com.lurdharry.currencyConverter.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;


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
    public Mono<ResponseDTO> getProviders() {
        var providers = currencyService.getProviderNames();
        return  Mono.just(ResponseDTO.builder()
                .data(providers)
                .message("Available currency providers")
                .status(HttpStatus.OK.value())
                .build()
        );
    }
}
