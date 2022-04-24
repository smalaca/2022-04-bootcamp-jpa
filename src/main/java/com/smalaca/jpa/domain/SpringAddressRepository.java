package com.smalaca.jpa.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringAddressRepository extends CrudRepository<Address, UUID> {
    List<Address> findAllByCity(String city);
    List<Address> findAllByCityAndStreet(String city, String street);
    Optional<Address> findOneByCityAndStreet(String city, String street);

    @Query("SELECT a FROM Address a WHERE a.city =:city")
    List<Address> findAllByCityName(@Param("city") String cityName);

    @Query("SELECT DISTINCT a.city FROM Address a ORDER BY a.city DESC")
    List<String> findAllCities();

    List<AddressInCountryProjection> findAllByCountry(String country);
}
