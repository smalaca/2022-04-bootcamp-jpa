package com.smalaca.jpa.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringAddressRepository extends CrudRepository<Address, UUID> {
    List<Address> findAllByCity(String city);
    List<Address> findAllByCityAndStreet(String city, String street);
    Optional<Address> findOneByCityAndStreet(String city, String street);
}
