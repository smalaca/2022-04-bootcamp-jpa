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

        UUID id = productRepository.save(new Product(UUID.randomUUID(), "Coffee", "The best drink for you"));
        productRepository.save(new Product(UUID.randomUUID(), "Tea", "Good to drink from time to time"));
        productRepository.save(new Product(UUID.randomUUID(), "Water", "You know you need it"));

        System.out.println("IN THE SAME CONTEXT - No Query");
        System.out.println(productRepository.findById(id));

        entityManager.close();

        EntityManager entityManagerTwo = entityManagerFactory.createEntityManager();
        ProductRepository productRepositoryTwo = new ProductRepository(entityManagerTwo);

        System.out.println("IN THE SECOND CONTEXT - With Query");
        System.out.println(productRepositoryTwo.findById(id));

        entityManagerTwo.close();
        entityManagerFactory.close();
    }
}
