package com.kjandgo.webfluxdemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjandgo.webfluxdemo.common.RoomSinkManager;
import com.kjandgo.webfluxdemo.domain.ChatDocument;
import com.kjandgo.webfluxdemo.service.ChatService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Component
@Slf4j
public class ChatWebSocketHandler implements WebSocketHandler {
    private final ChatService chatService;
    private final RoomSinkManager roomSinkManager;
    private final ObjectMapper mapper = new ObjectMapper();

    public ChatWebSocketHandler(ChatService chatService, RoomSinkManager roomSinkManager) {
        this.chatService = chatService;
        this.roomSinkManager = roomSinkManager;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        // 1) URI 경로에서 roomId 추출
        String path = session.getHandshakeInfo().getUri().getPath();
        String roomId = path.substring(path.lastIndexOf('/') + 1);

        // 2) 방 Sink 가져오기
        Sinks.Many<ChatDocument> sink = roomSinkManager.getSink(roomId);

        // 3) 클라이언트 → 서버 메시지 스트림 (receive)
        Mono<Void> input = session.receive()
                .map(WebSocketMessage::getPayloadAsText) // 받은 JSON 객체를 String 형태로 변환
                .flatMap(text -> parseIncoming(text, roomId))            // Flux<ChatDocument> 타입으로 맵핑
                .flatMap(chatService::save)                                 // DB에 채팅 기록 저장
                .doOnNext(sink::tryEmitNext)     // onNext() 실행마다 방 안 사용자에게 메세지 전송
                .doOnError(e -> log.info("에러 발생: {}", e.getMessage()))     // 에러 처리
                .then();

        // 4) 서버 → 클라이언트 (방 전체 실시간 스트림 + 과거 기록)
        Flux<WebSocketMessage> output =
                Flux.concat(                    // Flux 객체 순서대로 병합
                                chatService.history(roomId),    // 과거 메시지
                                sink.asFlux()                   // 실시간 메시지
                        )
                        .map(message -> session.textMessage(message.getSender() + " - " +  message.getMessage()));

        Mono<Void> outputSend = session.send(output);

        // 5) input / output 병렬 수행
        return Mono.when(input, outputSend);
    }

    private Mono<ChatDocument> parseIncoming(String json, String roomId) {
        try {
            Incoming dto = mapper.readValue(json, Incoming.class);
            return Mono.just(ChatDocument.builder()
                    .roomId(roomId)
                    .sender(dto.getSender())
                    .message(dto.getMessage())
                    .build());
        } catch (Exception e) {
            return Mono.empty();
        }
    }

    private String toJson(ChatDocument msg) {
        try {
            return mapper.writeValueAsString(msg);
        } catch (Exception e) {
            return "{}";
        }
    }

    @Data
    private static class Incoming {
        private String sender;
        private String message;
    }
}
