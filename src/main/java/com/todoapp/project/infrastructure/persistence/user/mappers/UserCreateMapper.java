package com.todoapp.project.infrastructure.persistence.user.mappers;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.enumerator.TypeUser;
import com.todoapp.project.modules.user.domain.validator.PasswordValidator;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import com.todoapp.project.modules.user.domain.valueobjects.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper {

    private final PasswordEncoder passwordEncoder;
    private final PasswordValidator passwordValidator;

    public UserCreateMapper(PasswordEncoder passwordEncoder, PasswordValidator passwordValidator) {
        this.passwordEncoder = passwordEncoder;
        this.passwordValidator = passwordValidator;
    }

    public UserEntity toEntity(UserCreateRequest userCreateRequest){
        Name name = new Name(userCreateRequest.name());
        Email email = new Email(userCreateRequest.email());
        Password password = Password.fromPlain(userCreateRequest.password(), passwordEncoder, passwordValidator);

        return new UserEntity(name, email, password, TypeUser.NORMAL);
    }

    public UserCreateResponse toResponse(UserEntity userEntity){
        return new UserCreateResponse(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue());
    }

}
