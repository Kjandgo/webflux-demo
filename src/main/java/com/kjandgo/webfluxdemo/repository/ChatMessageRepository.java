package com.kjandgo.webfluxdemo.repository;

import com.kjandgo.webfluxdemo.domain.ChatDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatDocument, String> {
    Flux<ChatDocument> findByRoomIdOrderByDateTimeAsc(String roomId);
}
