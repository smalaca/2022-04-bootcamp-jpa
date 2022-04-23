package com.smalaca.jpa;

import com.smalaca.jpa.domain.Product;
import com.smalaca.jpa.domain.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class JpaHelloWorld {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProductManagement");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ProductRepository productRepository = new ProductRepository(entityManager);

        productRepository.save(new Product("Coffee", "The best drink for you"));
        UUID productToModifyId = productRepository.save(new Product("Tea", "Good to drink from time to time"));
        productRepository.save(new Product("Water", "You know you need it"));
        productRepository.save(new Product("Lemon water", "This is something that is as good as water but tastes like lemon."));
        UUID productToRemoveId = productRepository.save(new Product("Coca Cola", "Cold as ice"));

        EntityManager entityManagerTwo = entityManagerFactory.createEntityManager();
        ProductRepository productRepositoryTwo = new ProductRepository(entityManagerTwo);
        productRepositoryTwo.deleteById(productToRemoveId);

        EntityManager entityManagerThree = entityManagerFactory.createEntityManager();
        ProductRepository productRepositoryThree = new ProductRepository(entityManagerThree);
        Product productToModify = productRepositoryThree.findById(productToModifyId);
        productToModify.changeDescription("Tea is better than Coffee");
        productRepositoryThree.update(productToModify);

        EntityManager entityManagerLast = entityManagerFactory.createEntityManager();
        ProductRepository productRepositoryLast = new ProductRepository(entityManagerLast);

        productRepositoryLast.findAll().forEach(System.out::println);

        entityManager.close();
        entityManagerLast.close();
        entityManagerFactory.close();
    }
}
