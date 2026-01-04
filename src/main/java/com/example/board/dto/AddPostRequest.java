package com.example.board.dto;

import org.springframework.util.Assert;
public record AddPostRequest(String title, String writer, String context) {

    public AddPostRequest {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(writer, "작성자는 필수입니다.");
        Assert.hasText(context, "내용은 필수입니다.");
    }
}

