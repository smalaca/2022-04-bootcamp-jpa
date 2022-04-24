package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Basket {
    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = BasketIdentifierConverter.class)
    @Column(name = "BASKET_ID", unique = true, nullable = false)
    private BasketIdentifier basketIdentifier;

    @ElementCollection
    @CollectionTable(name = "PRODUCTS_IN_BASKET")
    @MapKeyColumn(name = "PRODUCT_ID", unique = true)
    @Column(name = "AMOUNT")
    private Map<UUID, Integer> products = new HashMap<>();

    public Basket(BasketIdentifier basketIdentifier) {
        this.basketIdentifier = basketIdentifier;
    }

    public void add(UUID productId, int amount) {
        products.put(productId, products.getOrDefault(productId, 0) + amount);
    }
}
