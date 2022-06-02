package com.example.demo.service;

import com.example.demo.domain.Users;
import com.example.demo.models.SignupRequestDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.Errors;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        //회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<Users> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("ERROR : This ID already exists");
        }
        String nickname = requestDto.getNickname();
        Optional<Users> foundNickname = userRepository.findByUsername(nickname);
        if (foundNickname.isPresent()) {
            throw new IllegalArgumentException("ERROR : Nickname already EXISTS");
        }

        // 패스워드 암호화
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPassword();

        Users user = new Users(username, password, passwordCheck, nickname);
        userRepository.save(user);
    }

    public Boolean checkIdDuplicate(String username) {
        Users users = userRepository.findByUsername(username).orElse(null);

        try {
            if (users.getUsername().equals(username)) {
                return false;
            }
        } catch (NullPointerException e) {
            return true;
        }
        return true;
    }

    public Boolean checkPassword(SignupRequestDto requestDto) {
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        Users users = userRepository.findByUsername(passwordCheck).orElse(null);

        try {
            if (password.equals(passwordCheck)) {
                password = passwordEncoder.encode(requestDto.getPassword());
                passwordCheck = passwordEncoder.encode(requestDto.getPasswordCheck());
                return true;

            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validationResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validationResult.put(validKeyName, error.getDefaultMessage());
        }

        return validationResult;
    }
}
