package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class BuyerRepository {
    private final EntityManager entityManager;

    public BuyerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Buyer buyer) {
        entityManager.getTransaction().begin();
        entityManager.persist(buyer);
        entityManager.getTransaction().commit();
    }

    public List<Buyer> findAll() {
        return entityManager.createQuery("SELECT b FROM Buyer b", Buyer.class).getResultList();
    }
}
