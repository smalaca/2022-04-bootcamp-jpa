package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ToString
@Entity
public class Basket {
    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "PRODUCTS_IN_BASKET")
    @MapKeyColumn(name = "PRODUCT_ID", unique = true)
    @Column(name = "AMOUNT")
    private Map<UUID, Integer> products = new HashMap<>();

    public void add(UUID productId, int amount) {
        products.put(productId, products.getOrDefault(productId, 0) + amount);
    }
}
