package com.todoapp.project.infrastructure.persistence.auth.mapper;


import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterRequest;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserMapper {

    public UserRegisterResponse toResponse(UserEntity userEntity){
        return new UserRegisterResponse(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue(), userEntity.getType());
    }

}
