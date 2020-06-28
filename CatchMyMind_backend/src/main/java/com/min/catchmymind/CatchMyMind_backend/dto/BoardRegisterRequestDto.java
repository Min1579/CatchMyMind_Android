package com.min.catchmymind.CatchMyMind_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BoardRegisterRequestDto {
    private String title;
    private String content;

    public BoardRegisterRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
