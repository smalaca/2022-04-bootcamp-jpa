package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class InvoiceRepository {
    private final EntityManager entityManager;

    public InvoiceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Invoice invoice) {
        entityManager.getTransaction().begin();
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();
    }

    public List<Invoice> findAll() {
        return entityManager.createQuery("SELECT i FROM Invoice i", Invoice.class).getResultList();
    }
}
