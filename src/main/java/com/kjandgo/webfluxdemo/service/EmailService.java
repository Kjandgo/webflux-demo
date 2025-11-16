package com.kjandgo.webfluxdemo.service;

import com.kjandgo.webfluxdemo.dto.MailTxtSendDto;
import reactor.core.publisher.Mono;

public interface EmailService {
    Mono<Void> sendTxtEmail(MailTxtSendDto dto);
}
