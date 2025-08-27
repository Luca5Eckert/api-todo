package com.todoapp.project.modules.user.aplication.controller;

import com.todoapp.project.core.abstraction.UserAuthenticationService;
import com.todoapp.project.infrastructure.api.dto.ApiResponseDto;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.aplication.port.UserService;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/apitodo/user")
public class UserController {

    private final UserService userService;
    private final UserAuthenticationService userAuthenticationService;

    public UserController(UserService userService, UserAuthenticationService userAuthenticationService) {
        this.userService = userService;
        this.userAuthenticationService = userAuthenticationService;
        ;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<UserCreateResponse>> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        UUID id = userAuthenticationService.getIdUserAuthentication();

        UserCreateResponse userCreateResponse = userService.createUser(userCreateRequest, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.sucess(HttpStatus.CREATED.value(), "User created with success", userCreateResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserEditResponse>>  editUser(@RequestBody @Valid UserEditRequest userEditRequest, @PathVariable UUID id){
        UUID idExecuter = userAuthenticationService.getIdUserAuthentication();

        UserEditResponse userEditResponse = userService.editResponse(userEditRequest, idExecuter, id);

        return ResponseEntity.ok(ApiResponseDto.sucess(200, "User edited with sucess", userEditResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable UUID id){
        UUID idExecuter = userAuthenticationService.getIdUserAuthentication();

        UserDeleteRequest userDeleteRequest = new UserDeleteRequest(id);
        userService.deleteUser(userDeleteRequest, id);

        return ResponseEntity.ok(ApiResponseDto.sucess(200, "User deleted with sucess"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserGetResponse>> getUser(@PathVariable UUID id){

        UserGetRequest userGetRequest = new UserGetRequest(id);
        UserGetResponse userGetResponse = userService.getUser(userGetRequest);

        return ResponseEntity.ok(ApiResponseDto.sucess(200, "User found with sucess", userGetResponse));
    }


}
