package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemRepository {
    private final EntityManager entityManager;

    public ItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Item item) {
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public List<Item> findAll() {
        return entityManager
                .createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();

    }
}
