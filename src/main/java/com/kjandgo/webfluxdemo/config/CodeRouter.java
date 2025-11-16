package com.kjandgo.webfluxdemo.config;

import com.kjandgo.webfluxdemo.controller.CodeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CodeRouter {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(CodeHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/hello"), handler::hello)
                .andRoute(RequestPredicates.POST("/api/v2/hello"), handler::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> route(CodeHandler codeHandler) {

        return RouterFunctions.route(
                RequestPredicates.GET("/api/v2/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), codeHandler::hello);
    }
}
