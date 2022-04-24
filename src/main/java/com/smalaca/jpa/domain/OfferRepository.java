package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class OfferRepository {
    private final EntityManager entityManager;

    public OfferRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Offer offer) {
        entityManager.getTransaction().begin();
        entityManager.persist(offer);
        entityManager.getTransaction().commit();
    }

    public List<Offer> findAll() {
        return entityManager.createQuery("SELECT i FROM Offer i", Offer.class).getResultList();
    }
}
