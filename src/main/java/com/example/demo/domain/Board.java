package com.example.demo.domain;

import com.example.demo.models.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = true)
    private String unanimousPassword;

    @Column(nullable = false)
    private String username;

    public Board(String title, String contents, String unanimousPassword, String username) {
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.unanimousPassword = unanimousPassword;
    }

    public Board(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.unanimousPassword = requestDto.getUnanimousPassword();
    }

    public void update(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.unanimousPassword = requestDto.getUnanimousPassword();
    }
}
