package com.todoapp.project.modules.auth.domain.exception;

import jakarta.validation.ValidationException;

public class AuthenticationValidationException extends ValidationException {
    public AuthenticationValidationException(String message) {
        super(message);
    }
}
