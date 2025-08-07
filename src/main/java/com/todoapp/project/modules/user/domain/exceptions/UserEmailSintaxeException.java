package com.todoapp.project.modules.user.domain.exceptions;

public class UserEmailSintaxeException extends RuntimeException {
    public UserEmailSintaxeException(String message) {
        super(message);
    }
}
