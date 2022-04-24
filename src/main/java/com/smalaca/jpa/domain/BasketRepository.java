package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class BasketRepository {
    private final EntityManager entityManager;

    public BasketRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Basket basket) {
        entityManager.getTransaction().begin();
        entityManager.persist(basket);
        entityManager.getTransaction().commit();
    }

    public List<Basket> findAll() {
        return entityManager.createQuery("SELECT b FROM Basket b", Basket.class).getResultList();
    }
}
