package com.andrei.demo;

import com.andrei.demo.controller.CurrencyController;
import com.andrei.demo.models.ConversionRequest;
import com.andrei.demo.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@WebFluxTest(controllers = CurrencyController.class)
@Import(CurrencyService.class)
public class WebClientDemoApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void test() {
        ConversionRequest conversionRequest = new ConversionRequest("EUR", "USD", 10);

        webTestClient.post()
                .uri("/currency/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(conversionRequest), ConversionRequest.class)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.converted").isNotEmpty();

    }

    @Test
    void contextLoads() {
    }

}