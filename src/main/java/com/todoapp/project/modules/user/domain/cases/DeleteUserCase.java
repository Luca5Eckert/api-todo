package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;

public interface DeleteUserCase {

    void execute(UserDeleteRequest userDeleteRequest);
}
