package com.todoapp.project.modules.user.domain.exceptions.delete;

import com.todoapp.project.modules.user.domain.exceptions.UserValidationException;

public class UserDeleteValidationException extends UserValidationException {
    public UserDeleteValidationException(String message) {
        super(message);
    }
}
