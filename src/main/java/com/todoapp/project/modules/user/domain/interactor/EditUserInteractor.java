package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.EditUserCase;
import com.todoapp.project.modules.user.domain.exceptions.edit.UserEditValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;

import java.util.UUID;

public class EditUserInteractor implements EditUserCase {

    private final UserRepository userRepository;

    public EditUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEditResponse execute(UserEditRequest editRequest, UUID idExecuter, UUID idUser) {
        UserEntity userExecuter = userRepository.findById(idExecuter);
        UserEntity userEdit = userRepository.findById(idExecuter);

        validPermission(userEdit, userExecuter);

        editUser(userEdit, editRequest);

        userRepository.save(userEdit);

        return null;
    }

    private void editUser(UserEntity userEdit, UserEditRequest editRequest) {
        userEdit.setEmail(new Email(editRequest.email()));
        userEdit.setName(new Name(editRequest.name()));
    }

    private void validPermission(UserEntity userEdit, UserEntity userExecuter){
        if(!userExecuter.canEditUser() || !userExecuter.equals(userEdit)){
            throw new UserEditValidationException("\"User can't edit the another user\"");
        }
    }
}
