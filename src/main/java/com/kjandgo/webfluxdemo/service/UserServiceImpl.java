package com.kjandgo.webfluxdemo.service;

import com.kjandgo.webfluxdemo.domain.UserEntity;
import com.kjandgo.webfluxdemo.dto.MailTxtSendDto;
import com.kjandgo.webfluxdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService; // 반드시 리액티브하게 작성되어야 함

    @Override
    public Mono<UserEntity> findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public Mono<Integer> userRegister(UserEntity userEntity) {

        return this.findUserByUserId(userEntity.getUserId())

                // [CASE1] 아이디 존재 → 0 반환
                .flatMap(existingUser -> Mono.just(0))

                // [CASE2] 존재하지 않으면 저장
                .switchIfEmpty(userRepository.save(userEntity).flatMap(savedUser -> {
                    log.info("넘어온 userEntity: {}", savedUser);
                    MailTxtSendDto mailDto = MailTxtSendDto
                            .builder()
                            .emailAddr(savedUser.getUserEmail())
                            .subject("회원가입을 축하합니다!")
                            .content("환영합니다. 회원가입이 완료되었습니다.")
                            .build();

                    // 이메일은 비동기적으로 firing
                    emailService.sendTxtEmail(mailDto)
                            .subscribe(null, error -> log.error("이메일 전송 실패: {}", error.getMessage()));
                    return Mono.just(1);
                })).onErrorResume(e -> {
                    log.error("회원가입 처리 오류: {}", e.getMessage());
                    return Mono.just(0);
                });
    }
}
