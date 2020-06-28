package com.min.catchmymind.CatchMyMind_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterFormRequestDto {
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("password")
    private String password;

    UserRegisterFormRequestDto() {}

    public UserRegisterFormRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
