package com.todoapp.project.modules.auth.domain.service;

import com.todoapp.project.infrastructure.persistence.auth.mapper.LoginUserMapper;
import com.todoapp.project.infrastructure.persistence.auth.mapper.RegisterUserMapper;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginRequest;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginResponse;
import com.todoapp.project.modules.auth.aplication.port.AuthService;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterRequest;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterResponse;
import com.todoapp.project.modules.auth.domain.cases.LoginUserCase;
import com.todoapp.project.modules.auth.domain.cases.RegisterUserCase;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final RegisterUserCase registerUserCase;
    private final LoginUserCase loginUserCase;

    public AuthServiceImpl(RegisterUserCase registerUserCase, RegisterUserMapper registerUserMapper, LoginUserMapper loginUserMapper, LoginUserCase loginUserCase) {
        this.registerUserCase = registerUserCase;
        this.loginUserCase = loginUserCase;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return loginUserCase.execute(userLoginRequest);
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        return registerUserCase.execute(userRegisterRequest);
    }

}
