package com.example.preproject.service;

import com.example.preproject.dto.CommentRequestDto;
import com.example.preproject.dto.CommentResponseDto;
import com.example.preproject.entity.Board;
import com.example.preproject.entity.Comment;
import com.example.preproject.entity.UserEntity;
import com.example.preproject.repository.BoardRepository;
import com.example.preproject.repository.CommentRepository;
import com.example.preproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public CommentResponseDto writeComment(Long postId, CommentRequestDto requestDto, UserEntity user)
    {
        Board  board = boardRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(board, requestDto, user);
        commentRepository.save(comment);

        /*
        user = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );*/

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long postId, Long commentId, UserEntity user)
    {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        user = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );

        if(comment.getUser().getNickname().equals(user.getNickname())) {
            commentRepository.delete(comment);
        }
    }

    public CommentResponseDto updateComment(Long postId, Long commentId,
                                            CommentRequestDto requestDto, UserEntity user)
    {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        user = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );

        if(comment.getUser().getNickname().equals(user.getNickname())) {
            comment.updateComment(requestDto);
        }

        return new CommentResponseDto(comment);
    }
}
