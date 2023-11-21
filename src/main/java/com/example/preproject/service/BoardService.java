package com.example.preproject.service;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.dto.BoardResponseDto;
import com.example.preproject.dto.BoardSingleResponseDto;
import com.example.preproject.entity.Board;
import com.example.preproject.entity.UserEntity;
import com.example.preproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardResponseDto> getBoards()
    {
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();

        for(Board board : boardList)
        {
            boardResponseDtoList.add(new BoardResponseDto(board));
        }

        return boardResponseDtoList;
    }

    public BoardSingleResponseDto getSingleBoard(Long postId)
    {
        Board board = boardRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        return new BoardSingleResponseDto(board);
    }

    public BoardResponseDto updateBoard(Long postId, BoardRequestDto requestDto, UserEntity user)
    {
        Board board = boardRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        board.updateBoard(requestDto);

        return new BoardResponseDto(board);
    }

    public void deleteBoard(Long postId)
    {
        Board board = boardRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        boardRepository.delete(board);
    }

    public void writeBoard(BoardRequestDto requestDto, UserEntity user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("사용자 정보를 다시 확인해주세요 ");
        }

        Board board = new Board(requestDto);
        board.setUser(user); // UserEntity를 Board 엔티티에 설정
        boardRepository.save(board); // 반환값을 변수에 할당하지 않고 바로 저장
    }

    public void deleteBoard(Long postId, UserEntity currentUser) {
        Board board = boardRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        // 게시글의 작성자와 현재 요청한 사용자가 일치하는지 확인
        if (!board.getUser().equals(currentUser)) {
            throw new IllegalArgumentException("게시글을 삭제할 권한이 없습니다.");
        }

        boardRepository.delete(board);
    }
}
