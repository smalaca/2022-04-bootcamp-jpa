package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDefinitionRepository {
    private final EntityManager entityManager;

    public ProductDefinitionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(ProductDefinition productDefinition) {
        entityManager.getTransaction().begin();
        entityManager.persist(productDefinition);
        entityManager.getTransaction().commit();
    }

    public List<ProductDefinition> findAll() {
        return entityManager.createQuery("SELECT i FROM ProductDefinition i", ProductDefinition.class).getResultList();
    }
}
