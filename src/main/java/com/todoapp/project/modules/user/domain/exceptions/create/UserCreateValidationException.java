package com.todoapp.project.modules.user.domain.exceptions.create;

import com.todoapp.project.modules.user.domain.exceptions.UserValidationException;

public class UserCreateValidationException extends UserValidationException {
  public UserCreateValidationException(String message) {
    super(message);
  }
}
