package com.todoapp.project.infrastructure.persistence.furniture.converter;

import com.todoapp.project.modules.furniture.domain.valueobject.Description;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DescriptionConverter implements AttributeConverter<Description, String> {

    @Override
    public String convertToDatabaseColumn(Description description) {
        return description.getValue();
    }

    @Override
    public Description convertToEntityAttribute(String descriptionInText) {
        return new Description(descriptionInText);
    }
}
