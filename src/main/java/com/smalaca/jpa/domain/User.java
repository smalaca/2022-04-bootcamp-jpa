package com.smalaca.jpa.domain;

import javax.persistence.Entity;

@Entity
public class User extends Person {
    private long currentSessionLength;
    private long lastSessionLength;
}
