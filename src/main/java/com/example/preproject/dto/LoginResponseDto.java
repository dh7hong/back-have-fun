package com.example.preproject.dto;

import com.example.preproject.entity.UserEntity;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String username;
    private String token;

    public LoginResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public LoginResponseDto(UserEntity user)
    {
        this.username = user.getUsername();
        this.token = getToken();
    }
}