package com.kjandgo.webfluxdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetTestDTO {
    private String name;
    private int age;
    private String address;
}
