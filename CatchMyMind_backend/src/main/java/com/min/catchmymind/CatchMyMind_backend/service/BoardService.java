package com.min.catchmymind.CatchMyMind_backend.service;

import com.min.catchmymind.CatchMyMind_backend.dto.BoardRegisterRequestDto;
import com.min.catchmymind.CatchMyMind_backend.dto.BoardResponseDto;
import com.min.catchmymind.CatchMyMind_backend.dto.BoardsResponseDto;
import com.min.catchmymind.CatchMyMind_backend.exception.UserNotFoundException;
import com.min.catchmymind.CatchMyMind_backend.model.Board;
import com.min.catchmymind.CatchMyMind_backend.model.BoardRepository;
import com.min.catchmymind.CatchMyMind_backend.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Long registerBoard(Long userId, BoardRegisterRequestDto request) {
        return boardRepository.save(
                Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND!")))
                .build()
        ).getId();
    }

    public List<BoardsResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(board -> {
                    return new BoardsResponseDto(board.getId(),
                            board.getTitle(),
                            board.getUser().getName(),
                            board.getContent());
                }).collect(Collectors.toList());
    }

    public BoardResponseDto getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("BOARD NOT FOUND"));

        return BoardResponseDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .name(board.getUser().getName())
                .content(board.getContent())
                .build();
    }
}
