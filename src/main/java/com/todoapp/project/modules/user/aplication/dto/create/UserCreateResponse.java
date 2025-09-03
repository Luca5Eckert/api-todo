package com.todoapp.project.modules.user.aplication.dto.create;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserCreateResponse(@NotBlank long id,@NotBlank String name,@NotBlank String email){
}
