package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Author {
    @Id
    @GeneratedValue
    @Column(name = "AUTHOR_ID")
    private UUID id;

    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "AUTHOR_ID")
    private Set<Address> addresses = new HashSet<>();

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void add(Address address) {
        addresses.add(address);
    }
}
