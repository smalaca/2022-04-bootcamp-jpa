package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.UUID;

public class ToDoRepository {
    private final EntityManager entityManager;

    public ToDoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UUID save(ToDo toDo) {
        entityManager.getTransaction().begin();
        entityManager.persist(toDo);
        entityManager.getTransaction().commit();

        return toDo.getId();
    }

    public ToDo findById(UUID id) {
        return entityManager.find(ToDo.class, id);
    }
}
