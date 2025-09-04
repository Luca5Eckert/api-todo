package com.todoapp.project.modules.user.domain.service;

import com.todoapp.project.modules.user.domain.interactor.CreateUserInteractor;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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