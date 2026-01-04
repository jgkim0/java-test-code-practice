package com.example.board.dto;

import org.springframework.util.Assert;

public record UpdatePostRequest(Long id, String title, String context) {

    public UpdatePostRequest {
        Assert.hasText(title, "제목은 필수 값입니다.");
        Assert.hasText(context, "내용은 필수 값입니다.");
    }
}
