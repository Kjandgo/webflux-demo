package com.kjandgo.webfluxdemo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterResponse {

    private int result;   // 0 = 실패, 1 = 성공
    private String message;
}
