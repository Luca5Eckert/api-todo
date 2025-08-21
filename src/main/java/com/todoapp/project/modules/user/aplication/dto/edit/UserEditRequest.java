package com.todoapp.project.modules.user.aplication.dto.edit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserEditRequest(@NotBlank @Size(max = 10) String name,@NotBlank @Size(max = 30) String email) {
}
