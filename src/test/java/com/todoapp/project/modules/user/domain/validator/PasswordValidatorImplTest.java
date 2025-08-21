package com.todoapp.project.modules.user.domain.validator;

import com.todoapp.project.modules.user.domain.exceptions.password.PasswordValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorImplTest {

    @InjectMocks
    private PasswordValidatorImpl passwordValidator;

    @Test
    public void shouldThrowPasswordValidExceptionBecauseBadSintaxe(){
        String password = "withoutNumber@";

        PasswordValidException passwordValidException = assertThrows(PasswordValidException.class,() -> passwordValidator.valid(password));

        assertEquals("Password must contain at least one digit", passwordValidException.getMessage());
    }

    @Test
    public void shouldReturnTrue(){
        String password = "ThePasswordI5@Strong";

        boolean isValid = passwordValidator.valid(password);

        assertTrue(isValid);
    }

}