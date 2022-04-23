package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
public class ToDo {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String subject;

    private ToDo() {}

    public ToDo(String subject) {
        this.subject = subject;
    }

    UUID getId() {
        return id;
    }

    public void changeSubject(String subject) {
        this.subject = subject;
    }
}
