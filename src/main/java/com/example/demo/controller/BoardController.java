package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.models.BoardRequestDto;
import com.example.demo.repository.BoardRepository;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/api/post/boards")
    public Board creatBoard(@RequestBody BoardRequestDto requestDto) {
        System.out.println("요청");
        Board board = new Board(requestDto);
        System.out.println("요청2");
        return boardRepository.save(board);
    }

    @GetMapping("/api/get/boards")
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/delete/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/put/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.update(id, requestDto);
        return id;
    }
}
