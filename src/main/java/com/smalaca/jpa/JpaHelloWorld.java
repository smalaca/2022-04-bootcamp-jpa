package com.smalaca.jpa;

import com.smalaca.jpa.domain.Basket;
import com.smalaca.jpa.domain.BasketIdentifier;
import com.smalaca.jpa.domain.BasketRepository;
import com.smalaca.jpa.domain.Buyer;
import com.smalaca.jpa.domain.BuyerRepository;
import com.smalaca.jpa.domain.ContactDetails;
import com.smalaca.jpa.domain.Invoice;
import com.smalaca.jpa.domain.InvoiceRepository;
import com.smalaca.jpa.domain.Product;
import com.smalaca.jpa.domain.ProductRepository;
import com.smalaca.jpa.domain.Rating;
import com.smalaca.jpa.domain.Seller;
import com.smalaca.jpa.domain.SellerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class JpaHelloWorld {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProductManagement");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ProductRepository productRepository = new ProductRepository(entityManager);
        InvoiceRepository invoiceRepository = new InvoiceRepository(entityManager);
        BuyerRepository buyerRepository = new BuyerRepository(entityManager);
        SellerRepository sellerRepository = new SellerRepository(entityManager);
        BasketRepository basketRepository = new BasketRepository(entityManager);

        Basket basket = new Basket(new BasketIdentifier("tony stark", 42, LocalDate.now()));
        basket.add(UUID.randomUUID(), 13);
        basket.add(UUID.randomUUID(), 42);
        basketRepository.save(basket);

        buyerRepository.save(new Buyer(new ContactDetails("peter-parker", "123-456-789", "spiderman@marvel.com")));
        buyerRepository.save(new Buyer(new ContactDetails("steve-rogers", "111-222-333", "captain-america@marvel.com")));
        sellerRepository.save(new Seller(new ContactDetails("natasha romanoff", "NA", "black@widow.com")));
        sellerRepository.save(new Seller(new ContactDetails("wandaMaximoff", "987-654-321", "scarlet-witch@marvel.com")));

        invoiceRepository.save(new Invoice());
        Invoice invoiceOne = new Invoice();
        invoiceOne.sent();
        invoiceRepository.save(invoiceOne);
        Invoice invoiceTwo = new Invoice();
        invoiceTwo.payed();
        invoiceRepository.save(invoiceTwo);

        Product productWithAmount = new Product("Coffee", "The best drink for you", BigDecimal.valueOf(123.456));
        productWithAmount.addAmount(13);
        productRepository.save(productWithAmount);
        UUID productToModifyId = productRepository.save(new Product("Tea", "Good to drink from time to time", BigDecimal.valueOf(123456)));
        productRepository.save(new Product("Water", "You know you need it", BigDecimal.valueOf(0.123456)));
        productRepository.save(new Product("Lemon water", "This is something that is as good as water but tastes like lemon.", BigDecimal.valueOf(12345.6)));
        UUID productToRemoveId = productRepository.save(new Product("Coca Cola", "Cold as ice", BigDecimal.valueOf(12.3456)));

        Product productWithCategory = new Product("Pepsi", "Something like cola", BigDecimal.valueOf(13));
        productWithCategory.addCategory("Drinks");
        productWithCategory.addCategory("Pepsico");
        productRepository.save(productWithCategory);

        Product productWithRatings = new Product("Cup", "You need to have something to drink from", BigDecimal.valueOf(13));
        productWithRatings.add(new Rating("black panther", "13", "it is awesome"));
        productWithRatings.add(new Rating("natasha romanoff", "42", "it is great"));
        productRepository.save(productWithRatings);

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
        SellerRepository sellerRepositoryLast = new SellerRepository(entityManagerLast);
        BasketRepository basketRepositoryLast = new BasketRepository(entityManagerLast);

        productRepositoryLast.findAll().forEach(System.out::println);
        invoiceRepositoryLast.findAll().forEach(System.out::println);
        buyerRepositoryLast.findAll().forEach(System.out::println);
        sellerRepositoryLast.findAll().forEach(System.out::println);
        basketRepositoryLast.findAll().forEach(System.out::println);

        entityManager.close();
        entityManagerLast.close();
        entityManagerFactory.close();
    }
}
