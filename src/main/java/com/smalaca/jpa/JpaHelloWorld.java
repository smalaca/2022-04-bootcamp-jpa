package com.smalaca.jpa;

import com.smalaca.jpa.domain.Comment;
import com.smalaca.jpa.domain.Description;
import com.smalaca.jpa.domain.Item;
import com.smalaca.jpa.domain.ItemRepository;
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
        ToDo toDoWithComments = new ToDo("with comments");
        toDoWithComments.addComment(new Comment("Odinson", "I like it"));
        toDoWithComments.addComment(new Comment("Jane Foster", "Somehow I don't like it"));
        toDoWithComments.addComment(new Comment("Thor", "it's pointless to do it"));
        toDoRepository.save(toDoWithComments);

        ToDo toDoWithTags = new ToDo("something with tags");
        toDoWithTags.addTag("HOME", "to be done at home");
        toDoWithTags.addTag("WORK", "must be in the office");
        toDoRepository.save(toDoWithTags);

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
        List<ToDo> todos = toDoRepositoryLast.findAllWithCommentsAndTags();
        List<Item> items = itemRepositoryLast.findAll();
        entityManagerLast.close();

        todos.forEach(System.out::println);
        items.forEach(System.out::println);

        entityManagerFactory.close();
    }
}
