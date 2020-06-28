package com.min.catchmymind.CatchMyMind_backend.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class BoardsResponseDto {
    private Long boardId;
    private String title;
    private String name;
    private String content;

    @Builder
    public BoardsResponseDto(Long boardId, String title, String name, String content) {
        this.boardId = boardId;
        this.title = title;
        this.name = name;
        this.content = content;
    }
}
