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
    public Mono<ConversionResponse> convert(Mono<ConversionRequest> e) {
//        ConversionResponse responseMono = new ConversionResponse("EUR", "USD", 12, 13);

//        Mono<ConversionResponse> result = webClient.get()
////                .uri("/latest")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(CurrencyAPIResponse.class)
//                .flatMap(jsonObject -> {
//                    JSONObject rates =  (JSONObject) jsonObject.rates;
////                    String rates = (String) jsonObject.get("rates");
//                    double currency_rate = 0.1;
////                    String rates = jsonObject.rates;
////                    double currency_rate = (double) rates.get("USD");
////                    double currency_rate = jsonObject.rates.getDouble("USD");
//                    return e.map(p -> {
//                        ConversionResponse response = new ConversionResponse();
//                        response.setFrom(p.getFrom());
//                        response.setTo(p.getTo());
//                        response.setAmount(p.getAmount());
//                        response.setConverted(currency_rate);
//                        return response;
//                    });
//                });
//
//        return result;

        return e.map(request -> {
            ConversionResponse response = new ConversionResponse();
            response.setFrom(request.getFrom());
            response.setTo(request.getTo());
            response.setAmount(request.getAmount());
//            Mono<Double> rate = webClient.get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/latest")
//                            .queryParam("base", request.getFrom())
//                            .build())
//                    .retrieve()
//                    .bodyToMono(CurrencyAPIResponse.class)
//                    .map(jsonObject -> {
//                        double currency_rate = jsonObject.rates.get("USD");
//                        return currency_rate;
//                    });
//            rate.map(r -> response.setConverted(r));
            return response;
        });

//        double rate = webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/latest")
//                        .queryParam("base", "EUR")
//                        .build())
//                .retrieve()
//                .bodyToMono(CurrencyAPIResponse.class)
//                .map(jsonObject -> {
////                    JSONObject rates =  (JSONObject) jsonObject.getJSONObject("rates");
//                    double currency_rate = jsonObject.rates.get("USD");
//
////                    double currency_rate = (double) rates.get("USD");
//                    return currency_rate;
//                })
////                .bodyToMono(CurrencyAPIResponse.class)
////                .map(response -> response.rates)
//                .share().block();
//
//        return Mono.just(responseMono);
    }

    @Override
    public Mono<ConversionResponse> convert(ConversionRequest e) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("base", "EUR")
                        .build())
                .retrieve()
                .bodyToMono(CurrencyAPIResponse.class)
                .map(currencyAPIResponse -> {
                    ConversionResponse response = new ConversionResponse();
                    response.setFrom(e.getFrom());
                    response.setTo(e.getTo());
                    response.setAmount(e.getAmount());
                    double currency_rate = currencyAPIResponse.rates.get("USD");
                    response.setConverted(currency_rate * e.getAmount());

                    return response;
                });
    }
}