package com.andrei.demo.service;

import com.andrei.demo.models.ConversionRequest;
import com.andrei.demo.models.ConversionResponse;
import com.andrei.demo.models.CurrencyAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class CurrencyService implements ICurrencyService {
    @Autowired
    WebClient webClient;

    @Override
    public Mono<ConversionResponse> convert(ConversionRequest e) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("base", e.getTo())
                        .build())
                .retrieve()
                .bodyToMono(CurrencyAPIResponse.class)
                .map(currencyAPIResponse -> {
                    ConversionResponse response = new ConversionResponse();
                    response.setFrom(e.getFrom());
                    response.setTo(e.getTo());
                    response.setAmount(e.getAmount());
                    double currency_rate = currencyAPIResponse.rates.get(e.getFrom());
                    response.setConverted(currency_rate * e.getAmount());

                    return response;
                });
    }
}