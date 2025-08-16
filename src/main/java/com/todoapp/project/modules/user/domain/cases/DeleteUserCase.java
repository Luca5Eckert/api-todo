package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;

import java.util.UUID;

public interface DeleteUserCase {

    void execute(UserDeleteRequest userDeleteRequest, UUID id);
}
