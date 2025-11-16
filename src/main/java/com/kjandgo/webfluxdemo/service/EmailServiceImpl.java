package com.kjandgo.webfluxdemo.service;

import com.kjandgo.webfluxdemo.dto.MailTxtSendDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Override
    public Mono<Void> sendTxtEmail(MailTxtSendDto dto) {
        return Mono.fromRunnable(() -> {
            log.info("📨 이메일 전송 → {}", dto.getEmailAddr());
            // 실제 이메일 발송 로직(비동기)
        });
    }
}
