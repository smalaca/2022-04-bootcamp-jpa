package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
public class Invoice {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated
    private InvoiceStatus status = InvoiceStatus.CREATED;

    @OneToOne
    private Offer offer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<InvoiceItem> items = new ArrayList<>();

    public Invoice() {}

    public Invoice(Offer offer) {
        this.offer = offer;
    }

    public void sent() {
        status = InvoiceStatus.SENT;
    }

    public void payed() {
        status = InvoiceStatus.PAYED;
    }

    UUID getId() {
        return id;
    }
}
