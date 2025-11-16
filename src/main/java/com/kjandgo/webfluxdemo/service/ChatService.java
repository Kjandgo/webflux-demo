package com.kjandgo.webfluxdemo.service;

import com.kjandgo.webfluxdemo.domain.ChatDocument;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface ChatService {
    Mono<ChatDocument> save(ChatDocument msg);

    Flux<ChatDocument> history(String roomId);
}
