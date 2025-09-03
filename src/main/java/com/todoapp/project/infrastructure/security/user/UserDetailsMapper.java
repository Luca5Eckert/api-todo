package com.todoapp.project.infrastructure.security.user;

import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.validator.PasswordValidator;
import com.todoapp.project.modules.user.domain.valueobjects.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    private final PasswordEncoder passwordEncoder;
    private final PasswordValidator passwordValidator;

    public UserDetailsMapper(PasswordEncoder passwordEncoder, PasswordValidator passwordValidator) {
        this.passwordEncoder = passwordEncoder;
        this.passwordValidator = passwordValidator;
    }

    public UserDetailsAdapter toDetails(UserEntity userEntity){
        return new UserDetailsAdapter(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue(), userEntity.getPassword().raw(), userEntity.getType());
    }
}
