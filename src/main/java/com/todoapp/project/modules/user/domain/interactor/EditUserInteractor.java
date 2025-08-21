package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.infrastructure.persistence.user.mappers.UserEditMapper;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.EditUserCase;
import com.todoapp.project.modules.user.domain.exceptions.edit.UserEditValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;

import java.util.UUID;

public class EditUserInteractor implements EditUserCase {

    private final UserRepository userRepository;
    private final UserEditMapper userEditMapper;

    public EditUserInteractor(UserRepository userRepository, UserEditMapper userEditMapper) {
        this.userRepository = userRepository;
        this.userEditMapper = userEditMapper;
    }

    @Override
    public UserEditResponse execute(UserEditRequest editRequest, UUID idExecuter, UUID idUser) {
        UserEntity userExecuter = userRepository.findById(idExecuter).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));;
        UserEntity userEdit = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));;

        validPermission(userEdit, userExecuter);

        userEditMapper.toEntity(editRequest, userEdit);

        userRepository.save(userEdit);

        return userEditMapper.toResponse(userEdit);
    }

    private void validPermission(UserEntity userEdit, UserEntity userExecuter){
        if(userEdit.equals(userExecuter)){
            return;
        }

        if(!userExecuter.canEditUser()){
            throw new UserEditValidationException("User can't edit the another user");
        }
    }
}
