package com.todoapp.project.modules.auth.domain.interactors;

import com.todoapp.project.infrastructure.persistence.auth.mapper.RegisterUserMapper;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterRequest;
import com.todoapp.project.modules.auth.aplication.dto.register.UserRegisterResponse;
import com.todoapp.project.modules.auth.domain.cases.RegisterUserCase;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.exceptions.email.EmailAlreadyUseException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import com.todoapp.project.modules.user.domain.validator.PasswordValidator;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import com.todoapp.project.modules.user.domain.valueobjects.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserInteractor implements RegisterUserCase {

    private final UserRepository userRepository;
    private final RegisterUserMapper registerUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final PasswordValidator passwordValidator;

    public RegisterUserInteractor(UserRepository userRepository, RegisterUserMapper registerUserMapper, PasswordEncoder passwordEncoder, PasswordValidator passwordValidator) {
        this.userRepository = userRepository;
        this.registerUserMapper = registerUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.passwordValidator = passwordValidator;
    }

    @Override
    public UserRegisterResponse execute(UserRegisterRequest userRegisterRequest) {

        Name name = new Name(userRegisterRequest.name());
        Email email = new Email(userRegisterRequest.email());
        Password password = Password.fromPlain(userRegisterRequest.password(), passwordEncoder, passwordValidator);

        if(userRepository.findByEmail(email).isPresent()) throw new EmailAlreadyUseException("Email already in use");

        UserEntity userEntity = new UserEntity(name, email, password, TypeUser.NORMAL );

        userRepository.save(userEntity);

        return registerUserMapper.toResponse(userEntity);

    }


}
