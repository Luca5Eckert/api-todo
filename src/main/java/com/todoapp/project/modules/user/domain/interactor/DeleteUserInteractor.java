package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.DeleteUserCase;
import com.todoapp.project.modules.user.domain.exceptions.delete.UserDeleteValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;

import java.util.UUID;

public class DeleteUserInteractor implements DeleteUserCase {

    public final UserRepository userRepository;

    public DeleteUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UserDeleteRequest userDeleteRequest, UUID id) {
        UserEntity userExecuter = userRepository.findById(id);

        validUserPermissions(userExecuter);

        userRepository.deleteById(userDeleteRequest.id());
    }

    private void validUserPermissions(UserEntity userExecuter) {
        if(!userExecuter.canDeleteUser()){
            throw new UserDeleteValidationException("User can't delete another user");
        }
    }
}
