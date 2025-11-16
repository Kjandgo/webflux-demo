package com.kjandgo.webfluxdemo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailTxtSendDto {

    private String emailAddr;
    private String subject;
    private String content;
}
