package com.todoapp.project.infrastructure.persistence.mappers;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper {

    public UserEntity toEntity(UserCreateRequest userCreateRequest){
        Name name = new Name(userCreateRequest.name());
        Email email = new Email(userCreateRequest.email());

        return new UserEntity(name, email, userCreateRequest.password(), TypeUser.NORMAL);
    }

    public UserCreateResponse toResponse(UserEntity userEntity){
        return new UserCreateResponse(userEntity.getId(), userEntity.getName().getValue(), userEntity.getName().getValue());
    }

}
