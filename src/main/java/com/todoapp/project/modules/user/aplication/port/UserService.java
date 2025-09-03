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

    UserCreateResponse createUser(UserCreateRequest userCreateRequest, long id);

    UserGetResponse getUser(UserGetRequest userGetRequest);

    void deleteUser(UserDeleteRequest deleteRequest, long idUser);

    UserEditResponse editResponse(UserEditRequest userEditRequest, long idExecuter, long idUser);
}
