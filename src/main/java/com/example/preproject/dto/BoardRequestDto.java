package com.example.preproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BoardRequestDto {
    private String title;
    private String contents;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
