package com.smalaca.jpa;

import com.smalaca.jpa.domain.Description;
import com.smalaca.jpa.domain.Item;
import com.smalaca.jpa.domain.ItemRepository;
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
        ItemRepository itemRepository = new ItemRepository(entityManager);
        itemRepository.save(new Item(new Description("this is something important", "this is important because this and that")));

        ToDoRepository toDoRepository = new ToDoRepository(entityManager);

        toDoRepository.save(new ToDo("Things that must be done."));
        UUID toDoToDeleteId = toDoRepository.save(new ToDo("ToDo One"));
        toDoRepository.save(new ToDo("ToDo Two"));
        UUID toDoToModifyId = toDoRepository.save(new ToDo("ToDo Three"));
        ToDo toDoWithDetails = new ToDo("ToDo Four");
        toDoWithDetails.setDetails("Some description what I really want to do".repeat(10));
        toDoRepository.save(toDoWithDetails);
        ToDo toDoWithDescription = new ToDo("ToDo With descriptiong");
        toDoWithDescription.add(new Description("this is something important", "this is important because this and that"));
        toDoRepository.save(toDoWithDescription);

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
        ItemRepository itemRepositoryLast = new ItemRepository(entityManagerLast);
        toDoRepositoryLast.findAll().forEach(System.out::println);
        itemRepositoryLast.findAll().forEach(System.out::println);

        entityManagerFactory.close();
    }
}
