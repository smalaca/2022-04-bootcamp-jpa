package com.smalaca.jpa;

import com.smalaca.jpa.domain.Buyer;
import com.smalaca.jpa.domain.BuyerRepository;
import com.smalaca.jpa.domain.ContactDetails;
import com.smalaca.jpa.domain.Invoice;
import com.smalaca.jpa.domain.InvoiceRepository;
import com.smalaca.jpa.domain.Product;
import com.smalaca.jpa.domain.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.UUID;

public class JpaHelloWorld {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProductManagement");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ProductRepository productRepository = new ProductRepository(entityManager);
        InvoiceRepository invoiceRepository = new InvoiceRepository(entityManager);
        BuyerRepository buyerRepository = new BuyerRepository(entityManager);

        buyerRepository.save(new Buyer(new ContactDetails("peter-parker", "123-456-789", "spiderman@marvel.com")));
        buyerRepository.save(new Buyer(new ContactDetails("steve-rogers", "111-222-333", "captain-america@marvel.com")));

        invoiceRepository.save(new Invoice());
        Invoice invoiceOne = new Invoice();
        invoiceOne.sent();
        invoiceRepository.save(invoiceOne);
        Invoice invoiceTwo = new Invoice();
        invoiceTwo.payed();
        invoiceRepository.save(invoiceTwo);

        Product product = new Product("Coffee", "The best drink for you", BigDecimal.valueOf(123.456));
        product.add(13);
        productRepository.save(product);
        UUID productToModifyId = productRepository.save(new Product("Tea", "Good to drink from time to time", BigDecimal.valueOf(123456)));
        productRepository.save(new Product("Water", "You know you need it", BigDecimal.valueOf(0.123456)));
        productRepository.save(new Product("Lemon water", "This is something that is as good as water but tastes like lemon.", BigDecimal.valueOf(12345.6)));
        UUID productToRemoveId = productRepository.save(new Product("Coca Cola", "Cold as ice", BigDecimal.valueOf(12.3456)));

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
        InvoiceRepository invoiceRepositoryLast = new InvoiceRepository(entityManagerLast);
        BuyerRepository buyerRepositoryLast = new BuyerRepository(entityManagerLast);

        productRepositoryLast.findAll().forEach(System.out::println);
        invoiceRepositoryLast.findAll().forEach(System.out::println);
        buyerRepositoryLast.findAll().forEach(System.out::println);

        entityManager.close();
        entityManagerLast.close();
        entityManagerFactory.close();
    }
}
