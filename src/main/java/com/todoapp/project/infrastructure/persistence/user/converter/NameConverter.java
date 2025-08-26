package com.todoapp.project.infrastructure.persistence.user.converter;

import com.todoapp.project.modules.user.domain.valueobjects.Name;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NameConverter implements AttributeConverter<Name, String> {

    @Override
    public String convertToDatabaseColumn(Name name) {
        return name.getValue();
    }

    @Override
    public Name convertToEntityAttribute(String name) {
        return new Name(name);
    }

}
