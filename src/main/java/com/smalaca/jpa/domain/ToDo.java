package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.UUID;

@Entity
@Table(name = "TODOS")
@ToString
public class ToDo {
    @Id
    @GeneratedValue
    @Column(name = "TODO_ID")
    private UUID id;

    @Column(name = "TODO_SUBJECT")
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
