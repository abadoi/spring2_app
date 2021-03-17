//package com.andrei.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//@Configuration
//public class GreetingRouter {
//
//    @Bean
//    public RouterFunction<ServerResponse> route(GreetingsHandler greetingsHandler) {
//
//        return RouterFunctions.route()
//                .GET("/hello", RequestPredicates.accept(MediaType.APPLICATION_JSON), greetingsHandler::hello)
////                        request -> ServerResponse.ok().bodyValue("Hello ANDREI BO$$$$U"))
//                .POST("/currency/convert", RequestPredicates.accept(MediaType.APPLICATION_JSON), greetingsHandler::convert)
////                        request -> ServerResponse.ok().body(greetingsHandler.convert(request), ConversionResponse.class))
//                .build();
//
//
////        return RouterFunctions.route()
////                .route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingsHandler::hello)
////                .andRoute(RequestPredicates.POST("/currency/convert").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)).and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
////                        (request -> ServerResponse.ok().body(greetingsHandler.convert(request), ConversionResponse.class));
//    }
//
//}