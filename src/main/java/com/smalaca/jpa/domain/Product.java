package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@ToString
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private UUID id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "DESC", length = 200)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int amount;

    @Transient
    private String shortDescription;

    private Product() {}

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        changeDescription(description);
        this.price = price;
        amount = 0;
    }

    UUID getId() {
        return id;
    }

    public void add(int amount) {
        this.amount = amount;
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
