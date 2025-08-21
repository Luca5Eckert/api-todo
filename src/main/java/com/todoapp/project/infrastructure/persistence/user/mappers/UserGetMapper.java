package com.todoapp.project.infrastructure.persistence.user.mappers;

import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserGetMapper {

    public UserGetResponse toResponse(UserEntity userEntity){
        return new UserGetResponse(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue(), userEntity.getType());
    }
}
