package com.todoapp.project.infrastructure.persistence.auth.mapper;


import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserMapper {

    public UserRegisterResponse toResponse(UserEntity userEntity){
        return new UserRegisterResponse(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue(), userEntity.getType());
    }

}
