package com.min.catchmymind.CatchMyMind_backend.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String name;
    private String content;

    @Builder
    public BoardResponseDto(Long boardId, String title, String name, String content) {
        this.boardId = boardId;
        this.title = title;
        this.name = name;
        this.content = content;
    }
}
