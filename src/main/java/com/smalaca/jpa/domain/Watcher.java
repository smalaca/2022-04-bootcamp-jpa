package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Watcher {
    @Id
    @GeneratedValue
    @Column(name = "WATCHER_ID")
    private UUID id;

    private String login;

    @ManyToMany(mappedBy = "watchers")
    private Set<ToDo> toDos = new HashSet<>();

    public Watcher(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Watcher{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", toDos=" + toDos.size() +
                '}';
    }
}
