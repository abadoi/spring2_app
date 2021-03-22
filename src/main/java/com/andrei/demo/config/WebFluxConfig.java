package com.andrei.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer
{
    private final String BASE_URL = "https://api.exchangeratesapi.io/";

    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
        clientCodecConfigurer.customCodecs().register(new Jackson2JsonEncoder(new ObjectMapper(), APPLICATION_JSON));
        clientCodecConfigurer.customCodecs().register(new Jackson2JsonDecoder(new ObjectMapper(), APPLICATION_JSON));
    }

    @Bean
    public WebClient getWebClient()
    {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(10))
                                        .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true));


        return WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(connector)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
                .build();
    }
}