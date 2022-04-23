package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;

    private Product() {}

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    UUID getId() {
        return id;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
