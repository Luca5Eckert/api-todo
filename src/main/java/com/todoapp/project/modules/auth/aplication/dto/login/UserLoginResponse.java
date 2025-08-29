package com.todoapp.project.modules.auth.aplication.dto.login;

import java.util.UUID;

public record UserLoginResponse(UUID id, String username, String email, String token) {
}
