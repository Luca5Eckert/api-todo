package com.todoapp.project.modules.user.aplication.port;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;

import java.util.UUID;

public interface UserService {

    UserCreateResponse createUser(UserCreateRequest userCreateRequest, UUID id);

    UserGetResponse getUser(UserGetRequest userGetRequest);

    void deleteUser(UserDeleteRequest deleteRequest, UUID idUser);

    UserEditResponse editResponse(UserEditRequest userEditRequest, UUID idExecuter, UUID idUser);
}
