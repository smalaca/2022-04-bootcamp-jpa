package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OfferItem {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID productId;
    private int amount;

    public OfferItem(UUID productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
}
