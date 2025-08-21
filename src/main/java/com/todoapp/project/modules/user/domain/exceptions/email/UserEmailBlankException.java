package com.todoapp.project.modules.user.domain.exceptions.email;

import com.todoapp.project.modules.user.domain.exceptions.UserException;

public class UserEmailBlankException extends UserException {
    public UserEmailBlankException(String message) {
        super(message);
    }
}
