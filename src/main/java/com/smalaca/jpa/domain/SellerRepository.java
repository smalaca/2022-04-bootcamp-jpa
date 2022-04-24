package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class SellerRepository {
    private final EntityManager entityManager;

    public SellerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UUID save(Seller seller) {
        entityManager.getTransaction().begin();
        entityManager.persist(seller);
        entityManager.getTransaction().commit();
        return seller.getId();
    }

    public List<Seller> findAll() {
        return entityManager.createQuery("SELECT b FROM Seller b", Seller.class).getResultList();
    }

    public Seller findById(UUID id) {
        return entityManager.find(Seller.class, id);
    }

    public void update(Seller seller) {
        entityManager.getTransaction().begin();
        entityManager.merge(seller);
        entityManager.getTransaction().commit();
    }
}
