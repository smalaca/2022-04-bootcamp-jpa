package com.smalaca.jpa.domain;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;

public class BasketIdentifierConverter implements AttributeConverter<BasketIdentifier, String> {
    @Override
    public String convertToDatabaseColumn(BasketIdentifier basketIdentifier) {
        return basketIdentifier.getLogin() + ":" + basketIdentifier.getVisits() + ":" + basketIdentifier.getCreationDate();
    }

    @Override
    public BasketIdentifier convertToEntityAttribute(String value) {
        String[] parts = value.split(":");
        return new BasketIdentifier(parts[0], Integer.parseInt(parts[1]), LocalDate.parse(parts[2]));
    }
}
