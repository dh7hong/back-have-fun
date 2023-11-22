package com.example.preproject.entity;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.entity.UserEntity;
import com.example.preproject.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "board")
@EntityListeners(AuditingEntityListener.class)
public class    Board extends Timestamped implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

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

    public Board(BoardRequestDto requestDto, UserEntity user)
    {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
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
