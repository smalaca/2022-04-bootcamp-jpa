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

        UUID id = UUID.randomUUID();
        entityManager.persist(new ToDo(id, "Things that must be done."));
        entityManager.persist(new ToDo(UUID.randomUUID(), "ToDo One"));
        entityManager.persist(new ToDo(UUID.randomUUID(), "ToDo Two"));
        entityManager.persist(new ToDo(UUID.randomUUID(), "ToDo Three"));

        entityManager.getTransaction().commit();

        System.out.println("IN THE SAME CONTEXT - No Query to get ToDo");
        System.out.println(entityManager.find(ToDo.class, id));

        entityManager.close();

        EntityManager entityManagerTwo = entityManagerFactory.createEntityManager();

        System.out.println("IN THE SECOND CONTEXT - Query to get ToDo");
        System.out.println(entityManagerTwo.find(ToDo.class, id));

        entityManagerTwo.close();
        entityManagerFactory.close();
    }
}
