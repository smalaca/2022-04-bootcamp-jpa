package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Seller {
    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "phone", column = @Column(name = "SELLER_PHONE", length = 20)),
            @AttributeOverride(name = "mail", column = @Column(name = "SELLER_MAIL", length = 150))
    })
    private ContactDetails contactDetails;

    @OneToMany(mappedBy = "seller")
    private List<Invoice> invoices = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "DEFINITIONS_FOR_SELLER",
            joinColumns = @JoinColumn(name = "ID_OF_SELLER"),
            inverseJoinColumns = @JoinColumn(name = "ID_OF_PRODUCT_DEF")
    )
    private List<ProductDefinition> productDefinitions = new ArrayList<>();

    public Seller(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public UUID getId() {
        return id;
    }

    public void add(Invoice invoice) {
        invoices.add(invoice);
        invoice.assignTo(this);
    }

    public void add(ProductDefinition productDefinition) {
        productDefinitions.add(productDefinition);
    }
}
