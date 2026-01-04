package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.dto.AddPostRequest;
import com.example.board.dto.PostResponse;
import com.example.board.dto.UpdatePostRequest;
import com.example.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addPostRequest(@RequestBody AddPostRequest request) {
        Board post = new Board(request.title(), request.writer(), request.context());

        boardService.addBoard(post);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/view/{id}")
    public ResponseEntity<PostResponse> viewPost(@PathVariable("id") Long id) {
        Board board = boardService.viewPost(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(PostResponse.from(board));

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Board>> viewAllPost() {
        List<Board> boardList = boardService.findAll();

        return ResponseEntity.status(HttpStatus.CREATED).body(boardList);

    }

    @PostMapping("/update")
    public ResponseEntity<PostResponse> update(@RequestBody UpdatePostRequest updatePostRequest){

        boardService.update(updatePostRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
