package com.example.demo.controller;

import com.example.demo.models.SignupRequestDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        System.out.println(3333);
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() { return "signup"; }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        System.out.println(1234);
        userService.registerUser(requestDto);
        System.out.println(5678);
        return "redirect:/user/login";
    }

    @ResponseBody
    @GetMapping("/user/idDupCheck/{userid}")
    public Boolean idDupCheck(@PathVariable String userid) {

        return userService.checkIdDuplicate(userid);
    }

}