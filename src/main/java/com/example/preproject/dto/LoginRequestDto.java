package com.example.preproject.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}