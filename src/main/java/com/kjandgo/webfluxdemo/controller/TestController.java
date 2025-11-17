package com.kjandgo.webfluxdemo.controller;

import com.kjandgo.webfluxdemo.dto.GetTestDTO;
import com.kjandgo.webfluxdemo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/test")
@Slf4j
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

    @GetMapping("/monovoid")
    public Mono<Void> monoVoid() {
        return Mono.empty();
    }

    @GetMapping("/map/{num}")
    public void getMap(@PathVariable String num) {
//        Mono<Integer> mono = Mono.just(1);
//        mono.subscribe(System.out::println);


//        Flux<Flux<Integer>> fflux = Flux.just(Flux.range(1, 10),Flux.range(101, 10),Flux.range(1001, 10));
//        Flux<Integer> flux = fflux
//                .flatMap(i->i);
//
//        flux.subscribe(System.out::println);

        Mono<String> mono = Mono.just("mono - "+UUID.randomUUID().toString());
        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);

        Mono<String> mono2 = Mono.defer(()-> Mono.just("mono2 - "+UUID.randomUUID().toString()));
        mono2.subscribe(System.out::println);
        mono2.subscribe(System.out::println);
        mono2.subscribe(System.out::println);
        mono2.subscribe(System.out::println);
        mono2.subscribe(System.out::println);
        mono2.subscribe(System.out::println);
        mono2.subscribe(System.out::println);

        Flux<String> flux3 = Flux.create(emitter -> {
                    emitter.next("flux - "+UUID.randomUUID().toString());
                    emitter.next("flux - "+UUID.randomUUID().toString());
                    emitter.next("flux - "+UUID.randomUUID().toString());
                    emitter.next("flux - "+UUID.randomUUID().toString());
                    emitter.next("flux - "+UUID.randomUUID().toString());
                    emitter.complete();
                });
        flux3.subscribe(System.out::println);

    }
}
