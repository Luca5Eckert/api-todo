package com.todoapp.project.modules.user.aplication.dto.get;

import com.todoapp.project.modules.user.domain.enums.TypeUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserGetResponse(@NotNull UUID id, @NotBlank String name, @NotBlank String email, @NotBlank TypeUser type){
}
