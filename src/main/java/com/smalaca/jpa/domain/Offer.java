package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Offer {
    @Id
    @GeneratedValue
    private UUID id;

    private String offerNumber;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OfferItem> items = new ArrayList<>();

    public Offer(String offerNumber) {
        this.offerNumber = offerNumber;
    }

    public UUID getId() {
        return id;
    }

    public void add(OfferItem offerItem) {
        items.add(offerItem);
    }
}
