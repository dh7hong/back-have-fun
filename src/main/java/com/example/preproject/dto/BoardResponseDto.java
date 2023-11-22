package com.example.preproject.dto;

import com.example.preproject.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private int likes;
    private LocalDateTime createdAt;

    public BoardResponseDto(Board board)
    {
        this.id = board.getId();
        this.nickname = board.getUserNickname();
        this.title = board.getTitle();
        this.likes = board.getLikes();
        this.createdAt = board.getCreatedAt();
    }

}
