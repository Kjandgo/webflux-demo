package com.kjandgo.webfluxdemo.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TestServiceImpl implements TestService{

    @Override
    public void fluxTest() {
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5);
        //        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5,null);

        numbers.subscribe(
                // onNext - 각 데이터 처리
                data -> System.out.println("Received: " + data),

                // onError - 에러 처리
                error -> System.err.println("Error occurred: " + error),

                // onComplete - 완료 처리
                () -> System.out.println("Completed!"),

                // onSubscribe - 구독 시작 시 처리
                subscription -> {
                    System.out.println("Subscribed!");
                    subscription.request(Long.MAX_VALUE);
                }
        );
    }

    @Override
    public void fluxTest2() {
        // Reactive Programming 예시 (Project Reactor 사용)
        Integer[] array = {1, 2, 3, 4, 5, 6};

        // Publisher (데이터 발행)
        Flux<Integer> publisher = Flux.fromArray(array)
                .flatMap(value -> Flux.just(value))
                .filter(value -> value > 4);

        // Subscriber (데이터 구독)
        publisher.subscribe(
                // onNext - 데이터 수신시 처리
                value -> System.out.println("Received: " + value),
                // onError - 에러 발생시 처리
                error -> System.err.println("Error: " + error),
                // onComplete - 완료시 처리
                () -> System.out.println("Completed!")
        );
    }
}
