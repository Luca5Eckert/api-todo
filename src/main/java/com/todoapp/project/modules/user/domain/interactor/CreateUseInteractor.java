package com.todoapp.project.modules.user.domain.interactor;


import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import com.todoapp.project.modules.user.domain.exceptions.create.UserCreateValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateUseInteractor implements CreateUserCase  {

    private final UserRepository userRepository;

    public CreateUseInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserCreateRequest createUser(UserCreateRequest userCreateRequest, UUID id) {
        UserEntity user = userRepository.findById(id);

        validUserPermission(user);



        return null;
    }

    private void validUserPermission(UserEntity user){
        if(!user.canCreateUser()){
            throw new UserCreateValidationException("User can't create another user");
        }
    }

}
