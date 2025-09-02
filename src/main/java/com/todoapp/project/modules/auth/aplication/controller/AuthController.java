package com.todoapp.project.modules.auth.aplication.controller;

import com.todoapp.project.infrastructure.api.dto.ApiResponseDto;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginRequest;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginResponse;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterRequest;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterResponse;
import com.todoapp.project.modules.auth.aplication.port.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apitodo/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<UserLoginResponse>> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        UserLoginResponse userLoginResponse = authService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(201, "Logged in successfully", userLoginResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<UserRegisterResponse>> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        UserRegisterResponse userRegisterResponse = authService.register(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(201, "User registered successfully", userRegisterResponse));
    }

}
