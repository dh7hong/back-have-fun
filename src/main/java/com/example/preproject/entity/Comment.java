package com.example.preproject.entity;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Comment(CommentRequestDto requestDto)
    {
        this.text = requestDto.getText();
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