package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class OfferItemRepository {
    private final EntityManager entityManager;

    public OfferItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<OfferItem> findAll() {
        return entityManager.createQuery("SELECT i FROM OfferItem i", OfferItem.class).getResultList();
    }
}
