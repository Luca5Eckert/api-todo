package com.todoapp.project.modules.user.aplication.dto.create;

import java.util.UUID;

public record UserCreateResponse(UUID id, String name, String email){
}
