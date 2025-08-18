package com.todoapp.project.modules.user.aplication.dto.create;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
