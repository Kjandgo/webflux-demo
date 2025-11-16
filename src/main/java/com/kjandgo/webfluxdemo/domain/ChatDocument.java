package com.kjandgo.webfluxdemo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDocument {
    @Id
    private String id;

    private String roomId;
    private String sender;
    private String message;
    private String dateTime;
}
