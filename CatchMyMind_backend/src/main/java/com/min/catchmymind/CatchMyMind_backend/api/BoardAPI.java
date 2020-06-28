package com.min.catchmymind.CatchMyMind_backend.api;

import com.min.catchmymind.CatchMyMind_backend.dto.BoardRegisterRequestDto;
import com.min.catchmymind.CatchMyMind_backend.dto.BoardResponseDto;
import com.min.catchmymind.CatchMyMind_backend.dto.BoardsResponseDto;
import com.min.catchmymind.CatchMyMind_backend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardAPI {
    private final BoardService boardService;

    @GetMapping("api/board")
    public ResponseEntity<List<BoardsResponseDto>> getAllBoards() {
        return new ResponseEntity<>(boardService.getAllBoards(), HttpStatus.OK);
    }

    @GetMapping("api/board/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable("boardId") final Long boardId) {
        return new ResponseEntity<>(boardService.getBoardById(boardId), HttpStatus.OK);
    }

    @PostMapping("api/board/register/{userId}")
    public ResponseEntity<Long> registerBoard(@PathVariable("userId") final Long userId,
                                              @RequestBody final BoardRegisterRequestDto request) {
        return new ResponseEntity<>(boardService.registerBoard(userId, request), HttpStatus.OK);
    }

}
