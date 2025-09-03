package com.todoapp.project.modules.user.aplication.dto.delete;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDeleteRequest(@NotBlank long id) {
}
