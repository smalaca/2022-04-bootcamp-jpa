package com.smalaca.jpa.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringProductTwoRepository extends CrudRepository<ProductTwo, UUID> {
    List<ProductTwo> findAllByManufacturer(String manufacturer);
    Optional<ProductTwo> findOneByName(String name);
}
