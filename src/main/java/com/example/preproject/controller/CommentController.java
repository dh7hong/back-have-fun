package com.example.preproject.controller;

import com.example.preproject.dto.CommentRequestDto;
import com.example.preproject.dto.CommentResponseDto;
import com.example.preproject.entity.Comment;
import com.example.preproject.repository.CommentRepository;
import com.example.preproject.security.UserDetailsImpl;
import com.example.preproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class    CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> writeComment(@PathVariable Long postId,
                                                           @RequestBody CommentRequestDto requestDto,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        CommentResponseDto commentResponseDto = commentService.writeComment(postId, requestDto, userDetails.getUser());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long postId, @PathVariable Long commentId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try {
            commentService.deleteComment(postId, commentId, userDetails.getUser());
        } catch (Exception e)
        {
            return new ResponseEntity<>("댓글을 삭제하는 데 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("댓글이 삭제되었습니다.",HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long postId,
                                                            @PathVariable Long commentId,
                                                            @RequestBody CommentRequestDto requestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        CommentResponseDto commentResponseDto = commentService.updateComment(postId, commentId, requestDto, userDetails.getUser());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }
}
