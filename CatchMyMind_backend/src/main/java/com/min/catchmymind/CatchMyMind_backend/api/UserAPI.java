package com.min.catchmymind.CatchMyMind_backend.api;

import com.min.catchmymind.CatchMyMind_backend.dto.UserInfoResponseDto;
import com.min.catchmymind.CatchMyMind_backend.dto.UserLoginRequestDto;
import com.min.catchmymind.CatchMyMind_backend.dto.UserRegisterFormRequestDto;
import com.min.catchmymind.CatchMyMind_backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserAPI {
    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/user/{userId}")
    public ResponseEntity<UserInfoResponseDto> findUserById(@PathVariable("userId") final Long userId) {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @PostMapping("api/user/register")
    public ResponseEntity<Long> userRegister(@RequestBody final UserRegisterFormRequestDto request) {
        return new ResponseEntity<>(userService.userRegister(request), HttpStatus.OK);
    }

    @PostMapping("api/user/login")
    public ResponseEntity<Long> findUserByEmailAndPassword(@RequestBody final UserLoginRequestDto request) {
        return new ResponseEntity<>(userService.findUserByEmailAndPassword(request), HttpStatus.OK);
    }
}
