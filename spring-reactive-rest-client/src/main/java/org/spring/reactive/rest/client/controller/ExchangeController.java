package org.spring.reactive.rest.client.controller;

import org.spring.reactive.mongo.demo.domain.Exchange;
import org.spring.reactive.mongo.demo.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/{currency}")
    public Mono<Exchange> getExchangeInfo(@PathVariable("currency") String currency) {
        return exchangeService.getExchangeInfo(currency);
    }

}
