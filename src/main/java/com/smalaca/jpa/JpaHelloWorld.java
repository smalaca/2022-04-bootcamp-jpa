package com.smalaca.jpa;

import com.smalaca.jpa.domain.ToDo;
import com.smalaca.jpa.domain.ToDoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class JpaHelloWorld {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ThingsToBeDoneDomain");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ToDoRepository toDoRepository = new ToDoRepository(entityManager);

        UUID id = toDoRepository.save(new ToDo(UUID.randomUUID(), "Things that must be done."));
        toDoRepository.save(new ToDo(UUID.randomUUID(), "ToDo One"));
        toDoRepository.save(new ToDo(UUID.randomUUID(), "ToDo Two"));
        toDoRepository.save(new ToDo(UUID.randomUUID(), "ToDo Three"));

        System.out.println("IN THE SAME CONTEXT - No Query to get ToDo");
        System.out.println(toDoRepository.findById(id));

        entityManager.close();

        EntityManager entityManagerTwo = entityManagerFactory.createEntityManager();
        ToDoRepository toDoRepositoryTwo = new ToDoRepository(entityManagerTwo);

        System.out.println("IN THE SECOND CONTEXT - Query to get ToDo");
        System.out.println(toDoRepositoryTwo.findById(id));

        entityManagerTwo.close();
        entityManagerFactory.close();
    }
}
