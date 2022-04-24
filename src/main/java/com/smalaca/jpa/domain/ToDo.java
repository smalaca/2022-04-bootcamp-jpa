package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity(name = "ToDoEntity")
@Table(name = "TODOS")
@ToString
public class ToDo {
    @Id
    @GeneratedValue
    @Column(name = "TODO_ID")
    private UUID id;

    @Column(name = "TODO_SUBJECT", nullable = false)
    private String subject;

    @Column(name = "TODO_DETAILS", columnDefinition = "CLOB")
    private String details;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10)
    private ToDoStatus status = ToDoStatus.CREATED;

    @Transient
    private String firstSubjectLetter;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "longDescription", column = @Column(name = "LONG_DESCRIPTION")),
            @AttributeOverride(name = "shortDescription", column = @Column(name = "SHORT_DESCRIPTION"))
    })
    private Description description;

    @ElementCollection
    @CollectionTable(name = "TODO_COMMENTS", joinColumns = {@JoinColumn(name = "TODO_ID")})
    @Embedded
    private List<Comment> comments = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TODO_TAGS", joinColumns = {@JoinColumn(name = "TODO_ID")})
    @MapKeyColumn(name = "code")
    @Column(name = "description")
    private Map<String, String> tags = new HashMap<>();

    @Convert(converter = ToDoCategoryConverter.class)
    @Column(name = "TODO_CATEGORY")
    private ToDoCategory toDoCategory;

    private ToDo() {}

    public ToDo(String subject) {
        toDoCategory = new ToDoCategory("PERSONAL", "my own to do items");
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

    public void setDetails(String details) {
        this.details = details;
    }

    public void add(Description description) {
        this.description = description;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addTag(String code, String description) {
        tags.put(code, description);
    }
}
