package com.todoapp.project.modules.user.domain.exceptions;

public class UserNameBlankException extends RuntimeException {
  public UserNameBlankException(String message) {
    super(message);
  }
}
