package com.todoapp.project.modules.user.domain.exceptions.email;

import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;

public class UserNotFoundByEmailException extends UserNotFoundByIdException {
    public UserNotFoundByEmailException(String message) {
        super(message);
    }
}
