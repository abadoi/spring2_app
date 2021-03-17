//package com.andrei.demo;
//
//import com.andrei.demo.service.CurrencyService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//
//import reactor.core.publisher.Mono;
//
//
//@Component
//public class GreetingsHandler {
//
//    @Autowired
//    CurrencyService currencyService;
//
//    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();
//
//
//    public Mono<ServerResponse> hello(ServerRequest request) {
//        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
//                .body(BodyInserters.fromValue("Hello, Andrei bo$$u!"));
//    }
//
//
//    /**
//     * The handler to update a product.
//     * @param request
//     * @return - The conversion result as part of ServerResponse
//     */
//    public Mono<ServerResponse> convert(ServerRequest request){
//
//        return currencyService.convert(request.bodyToMono(ConversionRequest.class)).flatMap(conversionResponse ->
//                ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(conversionResponse, ConversionResponse.class)
//                        .switchIfEmpty(notFound));
//
////        Mono<ConversionRequest> requestMono = request.bodyToMono(ConversionRequest.class);
////        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
////                .body(BodyInserters.fromPublisher(currencyService.convert(requestMono), ConversionResponse.class))
////                .switchIfEmpty(notFound);
//
//
////        return currencyService.convert(request.bodyToMono(ConversionRequest.class))
////                .then(
////                ServerResponse.ok()
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .body(Mono.just(response), ConversionResponse.class))
////                .switchIfEmpty(notFound);
//
////        return request
////                .bodyToMono(ConversionRequest.class)
////                .map(to-> {
////                    System.out.println(to);
////                    return to;
////                })
////                .flatMap(plm ->
////                        ServerResponse
////                            .ok()
////                            .contentType(MediaType.APPLICATION_JSON)
////                            .body(response, ConversionResponse.class));
////                .then(ServerResponse
////                        .ok()
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .body(response, ConversionResponse.class));
//
////        return request.bodyToMono(ConversionRequest.class)
////                .flatMap();
//
////        Mono<String> response = request.bodyToMono(JSONObject.class).log()
////                .map(jsonObject -> {
////                    String to = (String) jsonObject.get("to");
////                    System.out.println(to);
////                    return to;
////                });
////
////        return ServerResponse.ok()
////                .body(response, String.class);
//
////        JSONPObject response = request.bodyToMono(JSONPObject.class).toProcessor().peek();
////        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
////                .body(BodyInserters.fromValue("Hello, Andrei 2!"));
////        return Mono.just(new ConversionResponse("EUR", "USD", 12, 11.0));
////        return responseMone.doOnNext(new ConversionResponse("EUR", "USD", 12, 11.0))
//    }
//}