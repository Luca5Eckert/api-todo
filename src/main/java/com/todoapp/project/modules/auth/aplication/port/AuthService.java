package com.todoapp.project.modules.auth.aplication.port;

import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginRequest;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginResponse;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterRequest;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterResponse;

public interface AuthService {

    UserLoginResponse login(UserLoginRequest userLoginRequest);

    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

}
