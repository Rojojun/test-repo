package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Users {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // null 허용 여부, 증복 허용 여부 (default = false)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String passwordCheck;

    public Users(String username, String password, String passwordCheck, String nickname) {
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
    }
}
