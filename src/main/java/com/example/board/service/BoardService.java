package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.UpdatePostRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BoardService {

    private static final AtomicLong SEQ = new AtomicLong(1);
    private final List<Board> boardList = new ArrayList<>();

    public void addBoard (final Board board) {

        Long id = SEQ.getAndIncrement();

        // ID 생성
        Board post = new Board(id, board.getTitle(), board.getWriter(), board.getContext());

        boardList.add(post);

    }


    public List<Board> findAll() {
        return boardList;
    }

    public Board viewPost(final Long id) {
        return boardList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);   // 또는 orElseThrow()
    }

    public void update(UpdatePostRequest updatePostRequest){
        Long id = updatePostRequest.id();

        Board prePost = viewPost(id);

        prePost.update(updatePostRequest.title(), updatePostRequest.context());

    }


}
