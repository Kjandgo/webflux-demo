package com.kjandgo.webfluxdemo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    private String id;

    private String userId;        // 로그인 ID
    private String userName;      // 유저 이름
    private String userEmail;     // 이메일
    private String password;      // 비밀번호 (암호화)
}
