package com.smalaca.jpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@RequiredArgsConstructor
@Getter(AccessLevel.PACKAGE)
public class BasketIdentifier {
    private final String login;
    private final int visits;
    private final LocalDate creationDate;
}
