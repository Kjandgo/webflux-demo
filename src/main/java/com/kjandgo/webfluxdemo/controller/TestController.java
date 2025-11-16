package com.kjandgo.webfluxdemo.controller;

import com.kjandgo.webfluxdemo.dto.GetTestDTO;
import com.kjandgo.webfluxdemo.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {
    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
    @GetMapping("/fluxtest")
    public void fluxTest() {
        testService.fluxTest();
    }

    @GetMapping("/fluxtest2")
    public void fluxTest2() {
        testService.fluxTest2();
    }

    @GetMapping("/gettest")
    public Mono<GetTestDTO> getTest() {
        return Mono.just(new GetTestDTO("강씨", 29, "제주도"));
    }
}
