package com.todoapp.project.modules.auth.domain.cases;

import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginRequest;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginResponse;

public interface LoginUserCase  {

    public UserLoginResponse execute(UserLoginRequest userLoginRequest);

}

