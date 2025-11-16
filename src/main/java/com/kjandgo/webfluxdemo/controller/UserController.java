package com.kjandgo.webfluxdemo.controller;

import com.kjandgo.webfluxdemo.domain.UserEntity;
import com.kjandgo.webfluxdemo.dto.UserRegisterRequest;
import com.kjandgo.webfluxdemo.dto.UserRegisterResponse;
import com.kjandgo.webfluxdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Mono<UserRegisterResponse> register(@RequestBody UserRegisterRequest req) {

        UserEntity user = UserEntity.builder()
                .userId(req.getUserId())
                .userName(req.getUserName())
                .userEmail(req.getEmail())
                .password(req.getPassword())
                .build();

        return userService.userRegister(user)
                .map(result -> {
                    if (result == 1) {
                        return new UserRegisterResponse(1, "회원가입 성공");
                    } else {
                        return new UserRegisterResponse(0, "이미 존재하는 ID이거나 실패했습니다.");
                    }
                });
    }
}
