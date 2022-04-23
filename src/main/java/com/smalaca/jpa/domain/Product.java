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
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;
    @Transient
    private String shortDescription;

    private Product() {}

    public Product(String name, String description) {
        this.name = name;
        changeDescription(description);
    }

    UUID getId() {
        return id;
    }

    public void changeDescription(String description) {
        this.description = description;
        recalculateShortDescription();
    }

    @PostLoad
    private void recalculateShortDescription() {
        if (description.length() > 40) {
            shortDescription = description.substring(0, 40) + "[...]";
        } else {
            shortDescription = description;
        }
    }
}
