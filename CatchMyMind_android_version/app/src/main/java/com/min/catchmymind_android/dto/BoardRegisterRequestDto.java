package com.min.catchmymind_android.dto;

public class BoardRegisterRequestDto {
    private String title;
    private String content;

    public BoardRegisterRequestDto() {}

    public BoardRegisterRequestDto(String title,String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
