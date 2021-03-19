package com.andrei.demo.service;

import com.andrei.demo.models.ConversionRequest;
import com.andrei.demo.models.ConversionResponse;
import com.andrei.demo.models.CurrencyAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;


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
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(3)))
                .onErrorResume(t -> Exceptions.isRetryExhausted(t), t ->
                        webClient.get()
                                .uri("https://api.exchangerate-api.com/v4/latest/" + e.getTo())
                                .accept(MediaType.TEXT_PLAIN)
                                .retrieve()
                                .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class) // error body as String or other class
                                        .flatMap(error -> Mono.error(new ResponseStatusException(response.statusCode(), "No conversion providers are currently available."))))
                                .bodyToMono(CurrencyAPIResponse.class))
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