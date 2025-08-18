package com.todoapp.project.modules.user.aplication.dto.edit;

import java.util.UUID;

public record UserEditResponse(UUID id, String name, String email){
}
