package com.andrei.demo;

import com.andrei.demo.models.ConversionRequest;
import com.andrei.demo.models.ConversionResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


public class Client {
    private WebClient client = WebClient.create("http://localhost:8080");

//    private Mono<ClientResponse> result = client.get()
//            .uri("/hello")
//            .accept(MediaType.TEXT_PLAIN)
//            .retrieve();

    private ConversionRequest conversionRequest = new ConversionRequest("EUR", "USD", 10);
    private Mono<ConversionResponse> result = client.post()
            .uri("/currency/convert")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(conversionRequest), ConversionRequest.class)
            .retrieve()
            .bodyToMono(ConversionResponse.class);
//            .share().block();


//    final Mono<ClientResponse> postResponse =
//            client
//                    .post()
//                    .uri("/people")
//                    .body(Mono.just(record), Person.class)
//                    .accept(APPLICATION_JSON)
//                    .exchange();
//    postResponse
//            .map(ClientResponse::statusCode)
//            .subscribe(status -> System.out.println("POST: " + status.getReasonPhrase()));

    public String getResult() {
        return ">> result = " + result.share().block();
    }
}
