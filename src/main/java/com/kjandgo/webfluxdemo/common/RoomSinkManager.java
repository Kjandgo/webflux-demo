package com.kjandgo.webfluxdemo.common;

import com.kjandgo.webfluxdemo.domain.ChatDocument;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoomSinkManager {

    private final Map<String, Sinks.Many<ChatDocument>> roomSinks = new ConcurrentHashMap<>();

    public Sinks.Many<ChatDocument> getSink(String roomId) {
        return roomSinks.computeIfAbsent(roomId, id ->
                Sinks.many().multicast().onBackpressureBuffer()
        );
    }
}