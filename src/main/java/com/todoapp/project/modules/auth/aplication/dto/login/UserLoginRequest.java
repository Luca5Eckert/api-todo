package com.todoapp.project.modules.auth.aplication.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequest(@NotBlank @Size(max = 60) String email, @NotBlank @Size(max = 180) String password) {
}
