package com.todoapp.project.modules.user.domain.interactor;


import com.todoapp.project.infrastructure.persistence.user.mappers.UserCreateMapper;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import com.todoapp.project.modules.user.domain.exceptions.create.UserCreateValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateUserInteractor implements CreateUserCase  {

    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;

    public CreateUserInteractor(UserRepository userRepository, UserCreateMapper userCreateMapper) {
        this.userRepository = userRepository;
        this.userCreateMapper = userCreateMapper;
    }

    @Override
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest, UUID id) {
        UserEntity user = userRepository.findById(id);

        validUserPermission(user);

        UserEntity userToCreate = userCreateMapper.toEntity(userCreateRequest);

        userRepository.save(userToCreate);

        return userCreateMapper.toResponse(userToCreate);
    }

    private void validUserPermission(UserEntity user){
        if(!user.canCreateUser()){
            throw new UserCreateValidationException("User can't create another user");
        }
    }

}
