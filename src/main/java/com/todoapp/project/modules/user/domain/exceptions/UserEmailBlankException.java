package com.todoapp.project.modules.user.domain.exceptions;

public class UserEmailBlankException extends RuntimeException {
    public UserEmailBlankException(String message) {
        super(message);
    }
}
