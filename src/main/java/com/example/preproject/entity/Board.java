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

    @Getter
    private String title;

    @Getter
    private String contents;

    @Getter
    private LocalDateTime createdAt = LocalDateTime.now();

    @Getter
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Getter
    private int likes;

    @Getter
    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Comment> commentsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        // 생성자 내에서 createdAt과 modifiedAt을 설정
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }


    public void updateBoard(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.modifiedAt = LocalDateTime.now(); // 업데이트 시 modifiedAt 갱신
    }

    public String getUserNickname() {
        return user.getNickname();
    }

}
