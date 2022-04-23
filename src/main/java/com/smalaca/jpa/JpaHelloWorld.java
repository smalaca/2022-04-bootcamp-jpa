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

        productRepository.save(new Product(UUID.randomUUID(), "Coffee", "The best drink for you"));
        productRepository.save(new Product(UUID.randomUUID(), "Tea", "Good to drink from time to time"));
        productRepository.save(new Product(UUID.randomUUID(), "Water", "You know you need it"));
        UUID productToRemoveId = productRepository.save(new Product(UUID.randomUUID(), "Coca Cola", "Cold as ice"));

        EntityManager entityManagerTwo = entityManagerFactory.createEntityManager();
        ProductRepository productRepositoryTwo = new ProductRepository(entityManagerTwo);
        productRepositoryTwo.deleteById(productToRemoveId);

        EntityManager entityManagerLast = entityManagerFactory.createEntityManager();
        ProductRepository productRepositoryLast = new ProductRepository(entityManagerLast);

        productRepositoryLast.findAll().forEach(System.out::println);

        entityManager.close();
        entityManagerLast.close();
        entityManagerFactory.close();
    }
}
