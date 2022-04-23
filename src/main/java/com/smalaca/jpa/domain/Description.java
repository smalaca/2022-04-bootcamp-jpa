package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Description {
    private String shortDescription;
    private String longDescription;

    public Description(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }
}
