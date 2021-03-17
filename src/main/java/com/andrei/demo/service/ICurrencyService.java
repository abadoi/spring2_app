package com.andrei.demo.service;

import com.andrei.demo.models.ConversionRequest;
import com.andrei.demo.models.ConversionResponse;
import reactor.core.publisher.Mono;

public interface ICurrencyService {
    Mono<ConversionResponse> convert(Mono<ConversionRequest> e);

    Mono<ConversionResponse> convert(ConversionRequest e);


}
