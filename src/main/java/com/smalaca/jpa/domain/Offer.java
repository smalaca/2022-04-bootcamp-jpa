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
public class Offer {
    @Id
    @GeneratedValue
    private UUID id;

    private String offerNumber;

    public Offer(String offerNumber) {
        this.offerNumber = offerNumber;
    }
}
