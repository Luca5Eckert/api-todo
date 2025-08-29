package com.todoapp.project.modules.auth.aplication.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(@NotBlank @Size(max = 25) String name,@NotBlank @Size(max = 60) String email,@NotBlank @Size(max = 180) String password) {
}
