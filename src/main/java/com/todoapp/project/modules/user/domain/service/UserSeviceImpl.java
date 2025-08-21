package com.todoapp.project.modules.user.domain.service;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.aplication.port.UserService;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import com.todoapp.project.modules.user.domain.cases.DeleteUserCase;
import com.todoapp.project.modules.user.domain.cases.EditUserCase;
import com.todoapp.project.modules.user.domain.cases.GetUserCase;

import java.util.UUID;

public class UserSeviceImpl implements UserService {

    private final CreateUserCase createUserCase;
    private final EditUserCase editUserCase;
    private final DeleteUserCase deleteUserCase;
    private final GetUserCase getUserCase;

    public UserSeviceImpl(CreateUserCase createUserCase, EditUserCase editUserCase, DeleteUserCase deleteUserCase, GetUserCase getUserCase) {
        this.createUserCase = createUserCase;
        this.editUserCase = editUserCase;
        this.deleteUserCase = deleteUserCase;
        this.getUserCase = getUserCase;
    }

    @Override
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest, UUID id) {
        return createUserCase.execute(userCreateRequest, id);
    }

    @Override
    public UserGetResponse getUser(UserGetRequest userGetRequest){
        return getUserCase.execute(userGetRequest);
    }

    @Override
    public void deleteUser(UserDeleteRequest deleteRequest, UUID id){
        deleteUserCase.execute(deleteRequest, id);
    }

    @Override
    public UserEditResponse editResponse(UserEditRequest userEditRequest, UUID idUser, UUID idExecuter){
        return editUserCase.execute(userEditRequest, idExecuter, idUser);
    }



}
