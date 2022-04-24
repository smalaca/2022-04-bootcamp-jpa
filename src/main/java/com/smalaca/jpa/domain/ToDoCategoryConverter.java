package com.smalaca.jpa.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ToDoCategoryConverter implements AttributeConverter<ToDoCategory, String> {
    @Override
    public String convertToDatabaseColumn(ToDoCategory toDoCategory) {
        return toDoCategory.getShortName() + ";" + toDoCategory.getFullName();
    }

    @Override
    public ToDoCategory convertToEntityAttribute(String value) {
        String[] parts = value.split(";");
        return new ToDoCategory(parts[0], parts[1]);
    }
}
