package com.todoapp.project.modules.user.domain.exceptions.edit;

import com.todoapp.project.modules.user.domain.exceptions.UserValidationException;

public class UserEditValidationException extends UserValidationException {
    public UserEditValidationException(String message) {
        super(message);
    }
}
