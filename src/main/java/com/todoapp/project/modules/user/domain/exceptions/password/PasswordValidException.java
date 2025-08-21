package com.todoapp.project.modules.user.domain.exceptions.password;

import com.todoapp.project.modules.user.domain.exceptions.UserException;

public class PasswordValidException extends UserException {
    public PasswordValidException(String message) {
        super(message);
    }
}
