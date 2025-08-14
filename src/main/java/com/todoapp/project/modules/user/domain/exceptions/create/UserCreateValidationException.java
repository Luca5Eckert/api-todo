package com.todoapp.project.modules.user.domain.exceptions.create;

public class UserCreateValidationException extends RuntimeException {
  public UserCreateValidationException(String message) {
    super(message);
  }
}
