package com.kjandgo.webfluxdemo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {

    private String userId;
    private String userName;
    private String email;
    private String password;
}
