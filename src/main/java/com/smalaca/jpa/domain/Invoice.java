package com.smalaca.jpa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

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

    public UUID getId() {
        return id;
    }

    void assignTo(Buyer buyer) {
        this.buyer = buyer;
    }

    void assignTo(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", status=" + status +
                ", offer=" + offer +
                ", items=" + items +
                ", buyer=" + (buyer == null ? "NO BUYER" : buyer.getId()) +
                ", seller=" + (seller == null ? "NO SELLER" : seller.getId()) +
                '}';
    }
}
