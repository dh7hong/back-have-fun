package com.example.preproject.dto;

import com.example.preproject.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String nickname;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment)
    {
        this.id = comment.getId();
        this.nickname = comment.getUserNickname();
        this.text = comment.getText();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
