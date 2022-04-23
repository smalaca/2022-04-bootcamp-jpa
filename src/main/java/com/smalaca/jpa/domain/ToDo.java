package com.smalaca.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ToDo {
    @Id
    private final UUID id;
    @Column
    private final String subject;

    public ToDo(UUID id, String subject) {
        this.id = id;
        this.subject = subject;
    }
}
