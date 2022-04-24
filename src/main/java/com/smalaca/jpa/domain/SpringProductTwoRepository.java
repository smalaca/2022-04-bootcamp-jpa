package com.smalaca.jpa.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringProductTwoRepository extends CrudRepository<ProductTwo, UUID> {
    List<ProductTwo> findAllByManufacturer(String manufacturer);
    List<ProductTwo> findAllByPriceBetween(int start, int end);
    Optional<ProductTwo> findOneByName(String name);
}
