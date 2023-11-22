package com.example.preproject.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String text;

    public String getText() {
        return text;
    }
}
