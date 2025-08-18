package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;

public interface EditUserCase {

    UserEditResponse execute(UserEditRequest editRequest);
}
