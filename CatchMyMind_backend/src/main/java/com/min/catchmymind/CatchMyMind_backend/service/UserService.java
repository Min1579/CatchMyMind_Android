package com.min.catchmymind.CatchMyMind_backend.service;

import com.min.catchmymind.CatchMyMind_backend.dto.UserInfoResponseDto;
import com.min.catchmymind.CatchMyMind_backend.dto.UserLoginRequestDto;
import com.min.catchmymind.CatchMyMind_backend.dto.UserRegisterFormRequestDto;
import com.min.catchmymind.CatchMyMind_backend.exception.UserNotFoundException;
import com.min.catchmymind.CatchMyMind_backend.model.User;
import com.min.catchmymind.CatchMyMind_backend.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserInfoResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        return UserInfoResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    @Transactional
    public Long userRegister(final UserRegisterFormRequestDto request) {
        return userRepository.save(
                User.builder().email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .build()
        ).getId();
    }

    @Transactional
    public Long findUserByEmailAndPassword(final UserLoginRequestDto request) {
        return userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
    }
}
