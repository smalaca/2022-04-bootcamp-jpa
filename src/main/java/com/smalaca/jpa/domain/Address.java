package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "ADDRESS_ID")
    private UUID id;

    private String street;
    private String postalCode;
    private String city;
    private String country;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    public Address(String street, String postalCode, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    void assignTo(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", author=" + author.getId() +
                '}';
    }
}
