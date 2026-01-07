package com.example.board.domain;


import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;


@Getter
public class Board {

    private Long id;
    private String title;
    private String writer;
    private LocalDateTime regDate = LocalDateTime.now();
    private String context;

    public Board(String title,  String writer, String context) {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(writer, "작성자는 필수입니다.");
        Assert.hasText(context, "내용은 필수입니다.");
        this.title = title;
        this.writer = writer;
        this.context = context;
    }

    public Board( Long id,  String title,  String writer,  String context) {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(writer, "작성자는 필수입니다.");
        Assert.hasText(context, "내용은 필수입니다.");
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.context = context;
    }

    public void update( String title,  String context) {
        if (title != null) {
            Assert.hasText(title, "제목은 필수입니다.");
            this.title = title;
        }

        if (context != null) {
            Assert.hasText(context, "내용은 필수입니다.");
            this.context = context;
        }
    }
}
