package com.example.demo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private String title;
    private String contents;
    private String unanimousPassword;
    private String username;
}
