package com.smalaca.jpa;

import com.smalaca.jpa.domain.Address;
import com.smalaca.jpa.domain.AddressRepository;
import com.smalaca.jpa.domain.Author;
import com.smalaca.jpa.domain.AuthorRepository;
import com.smalaca.jpa.domain.Description;
import com.smalaca.jpa.domain.Item;
import com.smalaca.jpa.domain.ItemRepository;
import com.smalaca.jpa.domain.ToDo;
import com.smalaca.jpa.domain.ToDoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JpaHelloWorld {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ThingsToBeDoneDomain");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ItemRepository itemRepository = new ItemRepository(entityManager);
        itemRepository.save(new Item(new Description("this is something important", "this is important because this and that")));

        ToDoRepository toDoRepository = new ToDoRepository(entityManager);
        AuthorRepository authorRepository = new AuthorRepository(entityManager);

        Author tonyStark = new Author("Tony", "Stark");
        tonyStark.add(new Address("Krakowska 3/2", "12345", "Kraków", "Polska"));
        tonyStark.add(new Address("Floriańska 13/42", "12345", "Kraków", "Polska"));
        tonyStark.add(new Address("Krakowska 67", "43212", "Warszawa", "Polska"));
        authorRepository.save(tonyStark);
        Author steveRogers = new Author("Steve", "Rogers");
        steveRogers.add(new Address("Krakowska 67", "43212", "Warszawa", "Polska"));
        steveRogers.add(new Address("Lubicz 7/7", "12345", "Kraków", "Polska"));
        authorRepository.save(steveRogers);

//        toDoRepository.save(new ToDo("Things that must be done.", tonyStark));
//        ToDo toDoWithTags = new ToDo("something with tags", steveRogers);
//        toDoWithTags.addTag("HOME", "to be done at home");
//        toDoWithTags.addTag("WORK", "must be in the office");
//        toDoRepository.save(toDoWithTags);


        EntityManager entityManagerLast = entityManagerFactory.createEntityManager();
        ToDoRepository toDoRepositoryLast = new ToDoRepository(entityManagerLast);
        ItemRepository itemRepositoryLast = new ItemRepository(entityManagerLast);
        AuthorRepository authorRepositoryLast = new AuthorRepository(entityManagerLast);
        AddressRepository addressRepositoryLast = new AddressRepository(entityManagerLast);
        List<ToDo> todos = toDoRepositoryLast.findAllWithCommentsAndTags();
//        List<Item> items = itemRepositoryLast.findAll();
        List<Author> authors = authorRepositoryLast.findAll();

        authors.forEach(System.out::println);
        addressRepositoryLast.findAll().forEach(System.out::println);
//        todos.forEach(System.out::println);
//        items.forEach(System.out::println);

        entityManagerFactory.close();
    }
}
