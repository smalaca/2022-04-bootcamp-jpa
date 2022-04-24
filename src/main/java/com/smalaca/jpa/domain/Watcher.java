package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Watcher {
    @Id
    @GeneratedValue
    @Column(name = "WATCHER_ID")
    private UUID id;

    private String login;

    public Watcher(String login) {
        this.login = login;
    }
}
