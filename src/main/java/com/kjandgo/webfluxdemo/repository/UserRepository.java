package com.kjandgo.webfluxdemo.repository;

import com.kjandgo.webfluxdemo.domain.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {

    Mono<UserEntity> findByUserId(String userId);
}
