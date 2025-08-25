package com.todoapp.project.infrastructure.persistence.user.converter;

import com.todoapp.project.modules.user.domain.valueobjects.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email.getValue();
    }

    @Override
    public Email convertToEntityAttribute(String email) {
        return new Email(email);
    }
}
