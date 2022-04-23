package com.smalaca.jpa.domain;

import javax.persistence.EntityManager;
import java.util.List;

public class SellerRepository {
    private final EntityManager entityManager;

    public SellerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Seller seller) {
        entityManager.getTransaction().begin();
        entityManager.persist(seller);
        entityManager.getTransaction().commit();
    }

    public List<Seller> findAll() {
        return entityManager.createQuery("SELECT b FROM Seller b", Seller.class).getResultList();
    }
}
