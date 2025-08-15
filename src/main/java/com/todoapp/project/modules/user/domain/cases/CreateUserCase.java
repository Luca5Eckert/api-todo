package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;

import java.util.UUID;

public interface CreateUserCase {

    UserCreateResponse createUser(UserCreateRequest userCreateRequest, UUID uuid);
}
