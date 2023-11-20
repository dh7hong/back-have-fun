package com.example.preproject.service;

import com.example.preproject.dto.CommentRequestDto;
import com.example.preproject.dto.CommentResponseDto;
import com.example.preproject.entity.Board;
import com.example.preproject.entity.Comment;
import com.example.preproject.repository.BoardRepository;
import com.example.preproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto writeComment(Long postId, CommentRequestDto requestDto)
    {
        Comment comment = new Comment(requestDto);
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long postId, Long commentId)
    {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        commentRepository.delete(comment);
    }

    public CommentResponseDto updateComment(Long postId, Long commentId,
                                            CommentRequestDto requestDto)
    {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        comment.updateComment(requestDto);

        return new CommentResponseDto(comment);
    }
}
