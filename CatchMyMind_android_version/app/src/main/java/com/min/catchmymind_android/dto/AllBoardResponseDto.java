package com.min.catchmymind_android.dto;

public class AllBoardResponseDto {
    private Long boardId;
    private String title;
    private String name;
    private String content;

    public AllBoardResponseDto() {}

    public AllBoardResponseDto(Long boardId, String title, String name, String content) {
        this.boardId = boardId;
        this.title = title;
        this.name = name;
        this.content = content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
