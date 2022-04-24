package com.smalaca.jpa.domain;

import lombok.ToString;

@ToString
public class ToDoCategory {
    private final String shortName;
    private final String fullName;

    public ToDoCategory(String shortName, String fullName) { //TODO_CATEGORY=shortName;fullName
        this.shortName = shortName;
        this.fullName = fullName;
    }

    String getShortName() {
        return shortName;
    }

    String getFullName() {
        return fullName;
    }
}
