package com.todoapp.project.modules.user.domain.service;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.interactor.CreateUserInteractor;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserSeviceImplTest {

    @InjectMocks
    private UserSeviceImpl userSevice;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateUserInteractor createUserCase;


    @Test
    void shouldHaveNotCreateUserAndThrow() {
        UserEntity userEntity = new UserEntity(UUID.randomUUID(), new Name("Lucas"), new Email("lucas@gmail.com"), "password", TypeUser.NORMAL, LocalDateTime.now(), LocalDateTime.now(), 1);
        UserCreateResponse userCreateResponse = new UserCreateResponse(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue());

        UserCreateResponse userCreateResponseTest = userSevice.createUser(new UserCreateRequest(userEntity.getName().getValue(), userEntity.getEmail().getValue(), userEntity.getPassword()), userEntity.getId());

        assertEquals(userCreateResponse, userCreateResponseTest);
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void editResponse() {
    }
}