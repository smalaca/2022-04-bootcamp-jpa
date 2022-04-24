package com.smalaca.jpa.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpringProductDefinitionRepository extends CrudRepository<ProductDefinition, UUID> {
}
