package com.todoapp.project.modules.user.domain.validator;

import org.springframework.stereotype.Component;

public interface PasswordValidator {

    boolean valid(String password);

}
