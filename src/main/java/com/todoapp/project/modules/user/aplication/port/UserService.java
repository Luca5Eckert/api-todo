package com.todoapp.project.modules.user.aplication.port;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;

public interface UserService {

    UserCreateResponse createUser(UserCreateRequest userCreateRequest);
}
