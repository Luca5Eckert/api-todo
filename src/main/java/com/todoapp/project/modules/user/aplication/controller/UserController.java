package com.todoapp.project.modules.user.aplication.controller;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/apitodo/user")
public class UserController {

    private final CreateUserCase createUserCase;

    public UserController(CreateUserCase createUserCase) {
        this.createUserCase = createUserCase;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        //Gera um id generico para fins de testes
        UUID id = UUID.randomUUID();
        UserCreateResponse userCreateResponse = createUserCase.execute(userCreateRequest, id);
        return ResponseEntity.ok(userCreateResponse);
    }

}
