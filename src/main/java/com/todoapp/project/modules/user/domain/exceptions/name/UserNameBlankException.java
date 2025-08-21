package com.todoapp.project.modules.user.domain.exceptions.name;

import com.todoapp.project.modules.user.domain.exceptions.UserException;

public class UserNameBlankException extends UserException {
  public UserNameBlankException(String message) {
    super(message);
  }
}
