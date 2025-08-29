package com.todoapp.project.modules.auth.domain.cases;

import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterRequest;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterResponse;

public interface RegisterUserCase {

    UserRegisterResponse execute(UserRegisterRequest userRegisterRequest);

}
