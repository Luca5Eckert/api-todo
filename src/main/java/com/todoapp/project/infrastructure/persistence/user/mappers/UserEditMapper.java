package com.todoapp.project.infrastructure.persistence.user.mappers;

import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;

public class UserEditMapper {

    public void toEntity(UserEditRequest userEditRequest, UserEntity user){
        user.setEmail(new Email(userEditRequest.email()));
        user.setName(new Name(userEditRequest.name()));
    }

    public UserEditResponse toResponse(UserEntity user){
        return new UserEditResponse(user.getId(), user.getName().getValue(), user.getEmail().getValue());
    }

}
