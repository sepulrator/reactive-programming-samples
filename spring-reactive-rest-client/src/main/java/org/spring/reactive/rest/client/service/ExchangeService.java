package org.spring.reactive.rest.client.service;

import org.spring.reactive.mongo.demo.domain.Exchange;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class ExchangeService {

    public Mono<Exchange> getExchangeInfo(String baseCurrency) {
        WebClient client = WebClient.create("http://api.fixer.io");
        Mono<Exchange> exchangeInfo = client.get()
                .uri(builder -> builder.path("/latest").queryParam("base", baseCurrency).build())
                .exchange()
                .flatMap(response -> response.bodyToMono(Exchange.class))
                .map(i-> i);
                /*.flatMap(response -> response.body((clientHttpResponse, context) -> {
                        Flux<DataBuffer> body = clientHttpResponse.getBody();

                        }
                );*/
        return exchangeInfo;
    }
}
