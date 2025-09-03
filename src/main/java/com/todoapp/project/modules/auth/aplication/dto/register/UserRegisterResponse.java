package com.todoapp.project.modules.auth.aplication.dto.register;

import com.todoapp.project.modules.user.domain.enums.TypeUser;

import java.util.UUID;

public record UserRegisterResponse(long id, String name, String email, TypeUser typeUser) {
}
