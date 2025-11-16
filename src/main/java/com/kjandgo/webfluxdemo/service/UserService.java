package com.kjandgo.webfluxdemo.service;

import com.kjandgo.webfluxdemo.domain.UserEntity;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<Integer> userRegister(UserEntity userEntity);

    Mono<UserEntity> findUserByUserId(String userId);
}
