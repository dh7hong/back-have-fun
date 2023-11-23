package com.example.preproject.controller;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.dto.BoardResponseDto;
import com.example.preproject.dto.BoardSingleResponseDto;
import com.example.preproject.entity.UserEntity;
import com.example.preproject.entity.UserRoleEnum;
import com.example.preproject.security.UserDetailsImpl;
import com.example.preproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public List<BoardResponseDto> getBoards()
    {
        return boardService.getBoards();
    }

    @GetMapping("/{postId}")
    public BoardSingleResponseDto getSingleBoard(@PathVariable Long postId)
    {
        return boardService.getSingleBoard(postId);
    }

    @Secured(UserRoleEnum.Authority.USER)
    @PutMapping("/{postId}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long postId,
                                                        @RequestBody BoardRequestDto requestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        BoardResponseDto boardResponseDto = boardService.updateBoard(postId, requestDto, userDetails.getUser());
        return new ResponseEntity<>(boardResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Long postId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{
            boardService.deleteBoard(postId, userDetails.getUser());
        } catch (Exception e)
        {
            return new ResponseEntity<>("게시글을 삭제하는 데 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> writeBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        boardService.writeNewBoard(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
