package com.min.catchmymind.CatchMyMind_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private Long id;
    private String email;
    private String name;

    @Builder
    public UserInfoResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

}
