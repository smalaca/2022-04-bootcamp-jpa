package com.smalaca.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "USER")
public class User extends Person {
    private long currentSessionLength;
    private long lastSessionLength;
}
