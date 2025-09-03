package com.todoapp.project.modules.user.aplication.dto.get;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserGetRequest(@NotNull long id) {
}
