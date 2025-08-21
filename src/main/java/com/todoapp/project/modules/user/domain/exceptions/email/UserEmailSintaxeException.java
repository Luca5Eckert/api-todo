package com.todoapp.project.modules.user.domain.exceptions.email;

import com.todoapp.project.modules.user.domain.exceptions.UserException;

public class UserEmailSintaxeException extends UserException {
    public UserEmailSintaxeException(String message) {
        super(message);
    }
}
