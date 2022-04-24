package com.smalaca.jpa.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpringToDoRepository extends CrudRepository<ToDo, UUID> {
}
