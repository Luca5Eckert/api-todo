package com.todoapp.project.modules.user.aplication.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(@NotBlank @Size(max = 10) String name, @NotBlank @Size(max = 30) String email, @NotBlank @Size(min = 8, max = 180) String password) {
}
