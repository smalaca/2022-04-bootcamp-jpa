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
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Buyer {
    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "BUY_LOGIN", nullable = false)),
            @AttributeOverride(name = "phone", column = @Column(name = "PHONE_NUMBER", length = 20)),
            @AttributeOverride(name = "mail", column = @Column(name = "MAIL_ADDRESS", length = 150))
    })
    private ContactDetails contactDetails;

    @OneToMany(mappedBy = "buyer")
    private List<Invoice> invoices = new ArrayList<>();

    public Buyer(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    UUID getId() {
        return id;
    }

    public void add(Invoice invoice) {
        invoices.add(invoice);
        invoice.assignTo(this);
    }
}
