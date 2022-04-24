package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;
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
//        TypedQuery<ToDo> query = entityManager.createQuery("SELECT t FROM ToDo t WHERE t.id = ?1", ToDo.class);
//        query.setParameter(1, id);
//        TypedQuery<ToDo> query = entityManager.createQuery("SELECT t FROM ToDo t WHERE t.id = :id", ToDo.class);
//        query.setParameter("id", id);

//        return query.getSingleResult();
        return entityManager.find(ToDo.class, id);
    }

    public List<ToDo> findAllWithCommentsAndTags() {
        return entityManager
                .createQuery("SELECT t FROM ToDoEntity t LEFT JOIN FETCH t.comments LEFT JOIN FETCH t.tags ORDER BY t.subject ASC", ToDo.class)
                .getResultList();
    }

    public List<ToDo> findAll() {
        return entityManager
                .createQuery("SELECT t FROM ToDoEntity t ORDER BY subject ASC", ToDo.class)
                .getResultList();

        // TRANSLATED TO QUERY:
        // SELECT * FROM TODOS AS t ORDER BY TODO_SUBJECT ASC
    }

    public void deleteById(UUID id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public void update(ToDo toDo) {
        entityManager.getTransaction().begin();
        entityManager.merge(toDo);
        entityManager.getTransaction().commit();
    }
}
