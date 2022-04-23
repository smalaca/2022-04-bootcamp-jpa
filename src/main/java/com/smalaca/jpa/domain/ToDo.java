package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
public class ToDo {
    @Id
    private UUID id;
    @Column
    private String subject;

    private ToDo() {}

    public ToDo(UUID id, String subject) {
        this.id = id;
        this.subject = subject;
    }
}
