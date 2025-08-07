package com.todoapp.project.modules.user.domain.exceptions.password;

public class PasswordValidException extends RuntimeException {
    public PasswordValidException(String message) {
        super(message);
    }
}
