package com.todoapp.project.infrastructure.persistence.user.converter;

import com.todoapp.project.modules.user.domain.valueobjects.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Converter(autoApply = true)
public class PasswordConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password password) {
        return password.raw();
    }

    @Override
    public Password convertToEntityAttribute(String password) {
        return Password.fromHash(password);
    }
}
