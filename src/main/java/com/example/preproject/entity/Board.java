package com.example.preproject.entity;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private int likes;

    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Comment> commentsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Board(BoardRequestDto requestDto)
    {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void updateBoard(BoardRequestDto requestDto)
    {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public int getLikes() {
        return likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public String getUserNickname() {
        return user.getNickname();
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }
}
