package com.example.preproject.entity;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.dto.CommentRequestDto;
import com.example.preproject.dto.CommentResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(CommentRequestDto requestDto)
    {
        this.text = requestDto.getText();
    }

    public Comment(Board board, CommentRequestDto requestDto, UserEntity user)
    {
        this.board = board;
        this.text = requestDto.getText();
        this.user = user;
    }

    public void updateComment(CommentRequestDto requestDto)
    {
        this.text = requestDto.getText();
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUserNickname() {
        return user.getNickname();
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
