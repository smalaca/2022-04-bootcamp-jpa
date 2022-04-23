package com.smalaca.jpa;

import com.smalaca.jpa.domain.ToDo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class JpaHelloWorld {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ThingsToBeDoneDomain");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(new ToDo(UUID.randomUUID(), "Things that must be done."));
        entityManager.persist(new ToDo(UUID.randomUUID(), "ToDo One"));
        entityManager.persist(new ToDo(UUID.randomUUID(), "ToDo Two"));
        entityManager.persist(new ToDo(UUID.randomUUID(), "ToDo Three"));

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
