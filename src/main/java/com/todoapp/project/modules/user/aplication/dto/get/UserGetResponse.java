package com.todoapp.project.modules.user.aplication.dto.get;

import com.todoapp.project.modules.user.domain.enumerator.TypeUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserGetResponse(@NotNull long id, @NotBlank String name, @NotBlank String email, @NotBlank TypeUser type){
}
