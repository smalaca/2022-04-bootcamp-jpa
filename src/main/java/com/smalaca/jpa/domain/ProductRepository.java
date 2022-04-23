package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.UUID;

public class ProductRepository {
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UUID save(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return product.getId();
    }

    public Product findById(UUID id) {
        return entityManager.find(Product.class, id);
    }
}
