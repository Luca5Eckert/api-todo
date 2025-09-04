package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.infrastructure.persistence.user.mappers.UserCreateMapper;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.exceptions.create.UserCreateValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserInteractorTest {

    private UserRepository userRepository;
    private UserCreateMapper userCreateMapper;
    private CreateUserInteractor createUserInteractor;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userCreateMapper = mock(UserCreateMapper.class);
        createUserInteractor = new CreateUserInteractor(userRepository, userCreateMapper);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        long id = 24342;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundByIdException.class, () ->
                createUserInteractor.execute(new UserCreateRequest("Test", "test@example.com", "4343eq"), id));
    }

    @Test
    void shouldThrowExceptionWhenUserHasNoPermission() {
        long id = 24342;
        UserEntity user = mock(UserEntity.class);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(user.canCreateUser()).thenReturn(false);

        assertThrows(UserCreateValidationException.class, () ->
                createUserInteractor.execute(new UserCreateRequest("Test", "test@example.com", "4343eq"), id));
    }

    @Test
    void shouldCreateUserWhenUserHasPermission() {
        long id = 24342;
        UserEntity user = mock(UserEntity.class);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(user.canCreateUser()).thenReturn(true);

        UserCreateRequest request = new UserCreateRequest("Test", "test@example.com", "3242");
        UserEntity createdEntity = mock(UserEntity.class);
        UserCreateResponse response = new UserCreateResponse(4654, "Test", "test@example.com");

        when(userCreateMapper.toEntity(request)).thenReturn(createdEntity);
        when(userCreateMapper.toResponse(createdEntity)).thenReturn(response);

        UserCreateResponse result = createUserInteractor.execute(request, id);

        assertEquals(response, result);

        // Verifica se o save foi chamado
        verify(userRepository).save(createdEntity);
    }
}
