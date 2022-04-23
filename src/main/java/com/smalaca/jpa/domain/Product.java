package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
public class Product {
    @Id
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;

    private Product() {}

    public Product(UUID id, String name, String description) {
        this.id = id;
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
