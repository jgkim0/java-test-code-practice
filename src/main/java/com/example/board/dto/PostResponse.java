package com.example.board.dto;

import com.example.board.domain.Board;
import java.time.LocalDateTime;


public record PostResponse(Long id, String title, String writer, String context, LocalDateTime regDate) {
    public static PostResponse from(Board board) {
        return new PostResponse(
                board.getId(),
                board.getTitle(),
                board.getWriter(),
                board.getContext(),
                board.getRegDate()
        );
    }
}
