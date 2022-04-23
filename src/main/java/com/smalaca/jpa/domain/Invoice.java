package com.smalaca.jpa.domain;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
public class Invoice {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated
    private InvoiceStatus status = InvoiceStatus.CREATED;

    public void sent() {
        status = InvoiceStatus.SENT;
    }

    public void payed() {
        status = InvoiceStatus.PAYED;
    }
}
