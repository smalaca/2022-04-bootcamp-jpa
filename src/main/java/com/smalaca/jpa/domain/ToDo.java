package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import java.util.UUID;

@Entity
@ToString
public class ToDo {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String subject;
    @Transient
    private String firstSubjectLetter;

    private ToDo() {}

    public ToDo(String subject) {
        changeSubject(subject);
    }

    UUID getId() {
        return id;
    }

    public void changeSubject(String subject) {
        this.subject = subject;
        recalculateFirstSubjectLetter();
    }

    @PostLoad
    private void recalculateFirstSubjectLetter() {
        firstSubjectLetter = subject.substring(0, 1);
    }
}
