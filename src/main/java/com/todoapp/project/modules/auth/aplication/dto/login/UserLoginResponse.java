package com.todoapp.project.modules.auth.aplication.dto.login;

import java.util.UUID;

public record UserLoginResponse(long id, String username, String email, String token) {
}
