package com.example.preproject.service;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.dto.BoardResponseDto;
import com.example.preproject.dto.BoardSingleResponseDto;
import com.example.preproject.entity.Board;
import com.example.preproject.entity.UserEntity;
import com.example.preproject.repository.BoardRepository;
import com.example.preproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

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

        user = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );

        if(board.getUser().getNickname().equals(user.getNickname())) {
            board.updateBoard(requestDto);
        }
        return new BoardResponseDto(board);
    }

    public void deleteBoard(Long postId, UserEntity user)
    {
        Board board = boardRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        user = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );

        if(board.getUser().getNickname().equals(user.getNickname())) {
            boardRepository.delete(board);
        }
    }

    @Transactional
    public void writeNewBoard(BoardRequestDto requestDto)
    {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        /*
        user = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );*/
    }
}
