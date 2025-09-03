package com.todoapp.project.modules.user.domain.exceptions.email;

import com.todoapp.project.modules.user.domain.exceptions.UserException;

public class EmailAlreadyUseException extends UserException {
    public EmailAlreadyUseException(String message) {
        super(message);
    }
}
