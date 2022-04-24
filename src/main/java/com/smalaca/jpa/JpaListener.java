package com.smalaca.jpa;

import com.smalaca.jpa.domain.Basket;
import com.smalaca.jpa.domain.BasketIdentifier;
import com.smalaca.jpa.domain.Buyer;
import com.smalaca.jpa.domain.Characteristic;
import com.smalaca.jpa.domain.ContactDetails;
import com.smalaca.jpa.domain.Invoice;
import com.smalaca.jpa.domain.Offer;
import com.smalaca.jpa.domain.OfferItem;
import com.smalaca.jpa.domain.Product;
import com.smalaca.jpa.domain.ProductDefinition;
import com.smalaca.jpa.domain.ProductTwo;
import com.smalaca.jpa.domain.Rating;
import com.smalaca.jpa.domain.Seller;
import com.smalaca.jpa.domain.SpringBasketRepository;
import com.smalaca.jpa.domain.SpringBuyerRepository;
import com.smalaca.jpa.domain.SpringInvoiceRepository;
import com.smalaca.jpa.domain.SpringOfferRepository;
import com.smalaca.jpa.domain.SpringProductDefinitionRepository;
import com.smalaca.jpa.domain.SpringProductRepository;
import com.smalaca.jpa.domain.SpringProductTwoRepository;
import com.smalaca.jpa.domain.SpringSellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaListener {
    private final SpringProductDefinitionRepository productDefinitionRepository;
    private final SpringProductRepository productRepository;
    private final SpringProductTwoRepository springProductTwoRepository;
    private final SpringInvoiceRepository invoiceRepository;
    private final SpringBuyerRepository buyerRepository;
    private final SpringSellerRepository sellerRepository;
    private final SpringBasketRepository basketRepository;
    private final SpringOfferRepository offerRepository;

    @EventListener
    @Transactional
    public void test(ContextRefreshedEvent event) {
        ProductDefinition drink = new ProductDefinition("DRINK", Set.of(Characteristic.SOFT, Characteristic.CHEAP));
        ProductDefinition fastFood = new ProductDefinition("FAST FOOD", Set.of(Characteristic.SMALL, Characteristic.CHEAP));
        productDefinitionRepository.save(drink);
        productDefinitionRepository.save(fastFood);

        Offer offerOne = new Offer(UUID.randomUUID().toString());
        Offer offerTwo = new Offer(UUID.randomUUID().toString());
        UUID offerWithInvoice = offerRepository.save(offerOne).getId();
        offerRepository.save(offerTwo);
        UUID offerWithoutInvoice = offerRepository.save(new Offer(UUID.randomUUID().toString())).getId();

        Offer offerWithItems = new Offer(UUID.randomUUID().toString());
        offerWithItems.add(new OfferItem(UUID.randomUUID(), 13));
        offerWithItems.add(new OfferItem(UUID.randomUUID(), 42));
        offerWithItems.add(new OfferItem(UUID.randomUUID(), 7));
        UUID offerWithItemsId = offerRepository.save(offerWithItems).getId();

        Basket basket = new Basket(new BasketIdentifier("tony stark", 42, LocalDate.now()));
        basket.add(UUID.randomUUID(), 13);
        basket.add(UUID.randomUUID(), 42);
        basketRepository.save(basket);

        UUID buyerId = buyerRepository.save(new Buyer(new ContactDetails("peter-parker", "123-456-789", "spiderman@marvel.com"))).getId();
        buyerRepository.save(new Buyer(new ContactDetails("steve-rogers", "111-222-333", "captain-america@marvel.com")));
        UUID sellerId = sellerRepository.save(new Seller(new ContactDetails("natasha romanoff", "NA", "black@widow.com"))).getId();
        sellerRepository.save(new Seller(new ContactDetails("wandaMaximoff", "987-654-321", "scarlet-witch@marvel.com")));

        UUID invoiceWithOfferOne = invoiceRepository.save(new Invoice(offerOne)).getId();
        Invoice invoiceTwo = new Invoice(offerTwo);
        invoiceTwo.sent();
        UUID invoiceWithOfferTwo = invoiceRepository.save(invoiceTwo).getId();
        Invoice invoiceThree = new Invoice();
        invoiceThree.payed();
        UUID invoiceWithoutOffer = invoiceRepository.save(invoiceThree).getId();

        Product productWithAmount = new Product("Coffee", "The best drink for you", BigDecimal.valueOf(123.456));
        productWithAmount.addAmount(13);
        productRepository.save(productWithAmount);
        UUID productToModifyId = productRepository.save(new Product("Tea", "Good to drink from time to time", BigDecimal.valueOf(123456))).getId();
        productRepository.save(new Product("Water", "You know you need it", BigDecimal.valueOf(0.123456)));
        productRepository.save(new Product("Lemon water", "This is something that is as good as water but tastes like lemon.", BigDecimal.valueOf(12345.6)));
        UUID productToRemoveId = productRepository.save(new Product("Coca Cola", "Cold as ice", BigDecimal.valueOf(12.3456))).getId();

        Product productWithCategory = new Product("Pepsi", "Something like cola", BigDecimal.valueOf(13));
        productWithCategory.addCategory("Drinks");
        productWithCategory.addCategory("Pepsico");
        productRepository.save(productWithCategory);

        Product productWithRatings = new Product("Cup", "You need to have something to drink from", BigDecimal.valueOf(13));
        productWithRatings.add(new Rating("black panther", "13", "it is awesome"));
        productWithRatings.add(new Rating("natasha romanoff", "42", "it is great"));
        productRepository.save(productWithRatings);

        productRepository.deleteById(productToRemoveId);

//        OfferRepository offerRepositoryThree = new OfferRepository(entityManagerThree);
//        offerRepositoryThree.deleteById(offerWithItemsId);
        Invoice toAddOne = invoiceRepository.findById(invoiceWithOfferOne).get();
        Invoice toAddTwo = invoiceRepository.findById(invoiceWithOfferTwo).get();
        Invoice toAddThree = invoiceRepository.findById(invoiceWithoutOffer).get();

        Buyer foundBuyer = buyerRepository.findById(buyerId).get();
        foundBuyer.add(toAddOne);
        foundBuyer.add(toAddTwo);
        buyerRepository.save(foundBuyer);
        Seller foundSeller = sellerRepository.findById(sellerId).get();
        foundSeller.add(toAddOne);
        foundSeller.add(toAddThree);
        foundSeller.add(drink);
        foundSeller.add(fastFood);
        sellerRepository.save(foundSeller);
        Seller carolDanvers = new Seller(new ContactDetails("carol danvers", "876678867", "captainM4rv3l@marvel.com"));
        carolDanvers.add(drink);
        sellerRepository.save(carolDanvers);

        Product productToModify = productRepository.findById(productToModifyId).get();
        productToModify.changeDescription("Tea is better than Coffee");
        productRepository.save(productToModify);


//        offerRepositoryLast.deleteById(offerWithoutInvoice);
//        invoiceRepositoryLast.deleteById(invoiceWithoutOffer);
//        offerRepositoryLast.deleteById(offerWithInvoice);
//        invoiceRepositoryLast.deleteById(invoiceWithOfferTwo);

//        productRepositoryLast.findAll().forEach(System.out::println);
//        invoiceRepositoryLast.findAll().forEach(System.out::println);
//        buyerRepositoryLast.findAll().forEach(System.out::println);
//        sellerRepository.findAll().forEach(System.out::println);
//        productDefinitionRepository.findAll().forEach(System.out::println);
//        basketRepositoryLast.findAll().forEach(System.out::println);
//        offerRepositoryLast.findAll().forEach(System.out::println);
//        offerItemRepositoryLast.findAll().forEach(System.out::println);

        springProductTwoRepository.save(new ProductTwo("Coca", "CocaCola", "drink", 123));
        springProductTwoRepository.save(new ProductTwo("Sprite", "CocaCola", "drink", 42));
        springProductTwoRepository.save(new ProductTwo("Fanta", "CocaCola", "drink", 12));
        springProductTwoRepository.save(new ProductTwo("Pepsi", "Pepsi", "drink", 98));
        springProductTwoRepository.save(new ProductTwo("7UP", "Pepsi", "drink", 67));
        springProductTwoRepository.save(new ProductTwo("Mirinda", "Pepsi", "drink", 43));

        springProductTwoRepository.findAll().forEach(System.out::println);
        System.out.println("PEPSI");
        springProductTwoRepository.findAllByManufacturer("Pepsi").forEach(System.out::println);
        System.out.println("COLA");
        springProductTwoRepository.findAllByManufacturer("CocaCola").forEach(System.out::println);
        System.out.println("Not existing manufacturer");
        springProductTwoRepository.findAllByManufacturer("NA").forEach(System.out::println);
        System.out.println("not existing product");
        System.out.println(springProductTwoRepository.findOneByName("NA").isEmpty());
        System.out.println("7UP");
        System.out.println(springProductTwoRepository.findOneByName("7UP").get());
    }
}
