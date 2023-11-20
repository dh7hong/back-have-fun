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

    public void writeBoard(BoardRequestDto requestDto)
    {
        Board board = new Board(requestDto);
        Board saveBoard = boardRepository.save(board);
    }
}
