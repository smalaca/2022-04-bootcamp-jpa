package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Rating {
    private String author;
    private String value;
    private String explanation;

    public Rating(String author, String value, String explanation) {
        this.author = author;
        this.value = value;
        this.explanation = explanation;
    }
}
