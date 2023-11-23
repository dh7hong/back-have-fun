package com.example.preproject.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String username;
    private String token;

    public LoginResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }

}
