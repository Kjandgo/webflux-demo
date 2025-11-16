package com.kjandgo.webfluxdemo.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CodeHandler {

    public Mono<ServerResponse> hello(ServerRequest serverRequest) {
        return Mono.just("Hello, world!")
                .subscribe(System.out::println);
    }
}
