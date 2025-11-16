package com.kjandgo.webfluxdemo.service;

import com.kjandgo.webfluxdemo.domain.ChatDocument;
import com.kjandgo.webfluxdemo.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatMessageRepository repository;

    public ChatServiceImpl(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public Mono<ChatDocument> save(ChatDocument msg) {
        msg.setDateTime(String.valueOf(Instant.now()));
        return repository.save(msg);
    }

    public Flux<ChatDocument> history(String roomId) {
        return repository.findByRoomIdOrderByDateTimeAsc(roomId);
    }
}
