package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Comment {
    @Column(name = "COMMENT_AUTHOR")
    private String author;

    @Column(name = "COMMENT_CONTENT")
    private String content;

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }
}
