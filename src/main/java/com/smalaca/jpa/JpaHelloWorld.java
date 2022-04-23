package com.smalaca.jpa;

import com.smalaca.jpa.domain.ToDo;
import com.smalaca.jpa.domain.ToDoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class JpaHelloWorld {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ThingsToBeDoneDomain");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ToDoRepository toDoRepository = new ToDoRepository(entityManager);

        toDoRepository.save(new ToDo("Things that must be done."));
        UUID toDoToDeleteId = toDoRepository.save(new ToDo("ToDo One"));
        toDoRepository.save(new ToDo("ToDo Two"));
        UUID toDoToModifyId = toDoRepository.save(new ToDo("ToDo Three"));



        EntityManager entityManagerTwo = entityManagerFactory.createEntityManager();
        ToDoRepository toDoRepositoryTwo = new ToDoRepository(entityManagerTwo);
        toDoRepositoryTwo.deleteById(toDoToDeleteId);



        EntityManager entityManagerThree = entityManagerFactory.createEntityManager();
        ToDoRepository toDoRepositoryThree = new ToDoRepository(entityManagerThree);
        ToDo toDoModified = toDoRepositoryThree.findById(toDoToModifyId);
        toDoModified.changeSubject("Something new needs to be done");
        toDoRepositoryThree.update(toDoModified);



        EntityManager entityManagerLast = entityManagerFactory.createEntityManager();
        ToDoRepository toDoRepositoryLast = new ToDoRepository(entityManagerLast);
        List<ToDo> todos = toDoRepositoryLast.findAll();
        todos.forEach(System.out::println);

        entityManagerFactory.close();
    }
}
