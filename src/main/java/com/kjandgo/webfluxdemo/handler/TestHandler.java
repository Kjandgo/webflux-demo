package com.kjandgo.webfluxdemo.handler;

import com.kjandgo.webfluxdemo.service.TestService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TestHandler {
    private final TestService testService;
    public TestHandler(TestService testService) {
        this.testService = testService;
    }

    public Mono<ServerResponse> fluxTest(ServerRequest req){
        testService.fluxTest();
        return Mono.empty();
    }

    public Mono<ServerResponse> fluxTest2(ServerRequest req){
        testService.fluxTest2();
        return Mono.empty();
    }

}
