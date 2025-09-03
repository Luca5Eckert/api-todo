package com.todoapp.project.modules.user.aplication.dto.edit;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserEditResponse(@NotBlank long id,@NotBlank String name,@NotBlank String email){
}
