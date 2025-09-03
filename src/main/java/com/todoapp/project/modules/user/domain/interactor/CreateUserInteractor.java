package com.todoapp.project.modules.user.domain.interactor;


import com.todoapp.project.infrastructure.persistence.user.mappers.UserCreateMapper;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import com.todoapp.project.modules.user.domain.exceptions.create.UserCreateValidationException;
import com.todoapp.project.modules.user.domain.exceptions.email.EmailAlreadyUseException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
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
    public UserCreateResponse execute(UserCreateRequest userCreateRequest, long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));;

        validUserPermission(user);

        if(userRepository.findByEmail(new Email(userCreateRequest.email())).isPresent()) throw new EmailAlreadyUseException("Email already in use");

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
