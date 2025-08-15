package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;

import java.util.UUID;

public interface CreateUserCase {

    UserCreateRequest createUser(UserCreateRequest userCreateRequest, UUID uuid);
}
