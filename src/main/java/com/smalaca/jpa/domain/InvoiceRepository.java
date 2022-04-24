package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class InvoiceRepository {
    private final EntityManager entityManager;

    public InvoiceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UUID save(Invoice invoice) {
        entityManager.getTransaction().begin();
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();

        return invoice.getId();
    }

    public List<Invoice> findAll() {
        return entityManager.createQuery("SELECT i FROM Invoice i", Invoice.class).getResultList();
    }

    public void deleteById(UUID id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    private Invoice findById(UUID id) {
        return entityManager.find(Invoice.class, id);
    }
}
