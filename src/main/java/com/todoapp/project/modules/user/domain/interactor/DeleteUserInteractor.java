package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.domain.cases.DeleteUserCase;
import com.todoapp.project.modules.user.domain.port.UserRepository;

public class DeleteUserInteractor implements DeleteUserCase {

    public final UserRepository userRepository;

    public DeleteUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UserDeleteRequest userDeleteRequest) {
        userRepository.deleteById(userDeleteRequest.id());
    }
}
