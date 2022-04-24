package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class WatcherRepository {
    private final EntityManager entityManager;

    public WatcherRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Watcher watcher) {
        entityManager.getTransaction().begin();
        entityManager.persist(watcher);
        entityManager.getTransaction().commit();
    }

    public List<Watcher> findAll() {
        return entityManager
                .createQuery("SELECT i FROM Watcher i", Watcher.class)
                .getResultList();

    }
}
