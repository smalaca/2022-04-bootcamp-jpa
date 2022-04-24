package com.smalaca.jpa;

import com.smalaca.jpa.domain.Address;
import com.smalaca.jpa.domain.Author;
import com.smalaca.jpa.domain.Description;
import com.smalaca.jpa.domain.Item;
import com.smalaca.jpa.domain.SpringAddressRepository;
import com.smalaca.jpa.domain.SpringAuthorRepository;
import com.smalaca.jpa.domain.SpringItemRepository;
import com.smalaca.jpa.domain.SpringToDoRepository;
import com.smalaca.jpa.domain.SpringWatcherRepository;
import com.smalaca.jpa.domain.ToDo;
import com.smalaca.jpa.domain.Watcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JpaListener {
    private final SpringItemRepository itemRepository;
    private final SpringToDoRepository toDoRepository;
    private final SpringAuthorRepository authorRepository;
    private final SpringWatcherRepository watcherRepository;
    private final SpringAddressRepository addressRepository;

    @EventListener
    @Transactional
    public void test(ContextRefreshedEvent event) {
        itemRepository.save(new Item(new Description("this is something important", "this is important because this and that")));

        Watcher charlesXavier = new Watcher("charles xavier");
        Watcher logan = new Watcher("logan");
        Watcher jeanGrey = new Watcher("jean grey");
        watcherRepository.save(charlesXavier);
        watcherRepository.save(logan);
        watcherRepository.save(jeanGrey);

        Author tonyStark = new Author("Tony", "Stark");
        tonyStark.add(new Address("Krakowska 3/2", "12345", "Kraków", "Polska"));
        tonyStark.add(new Address("Floriańska 13/42", "12345", "Kraków", "Polska"));
        tonyStark.add(new Address("Krakowska 67", "43212", "Warszawa", "Polska"));
        authorRepository.save(tonyStark);
        Author steveRogers = new Author("Steve", "Rogers");
        steveRogers.add(new Address("Krakowska 67", "43212", "Warszawa", "Polska"));
        steveRogers.add(new Address("Lubicz 7/7", "12345", "Kraków", "Polska"));
        authorRepository.save(steveRogers);

        toDoRepository.save(new ToDo("Things that must be done.", tonyStark));
        ToDo toDoWithTags = new ToDo("something with tags", steveRogers);
        toDoWithTags.addTag("HOME", "to be done at home");
        toDoWithTags.addTag("WORK", "must be in the office");
        toDoWithTags.add(logan);
        toDoWithTags.add(jeanGrey);
        toDoWithTags.add(charlesXavier);
        toDoRepository.save(toDoWithTags);

        Iterable<ToDo> todos = toDoRepository.findAll();
//        List<Item> items = itemRepositoryLast.findAll();
//        List<Author> authors = authorRepositoryLast.findAll();

//        authors.forEach(System.out::println);
//        addressRepositoryLast.findAll().forEach(System.out::println);
        watcherRepository.findAll().forEach(System.out::println);
        todos.forEach(System.out::println);
//        items.forEach(System.out::println);
    }
}
