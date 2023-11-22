package com.example.preproject.dto;

import com.example.preproject.entity.Board;
import com.example.preproject.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardSingleResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private List<Comment> commentList;
    public BoardSingleResponseDto(Board board)
    {
        this.id = board.getId();
        this.nickname = board.getUserNickname();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
        this.commentList = board.getCommentsList();
    }
}
