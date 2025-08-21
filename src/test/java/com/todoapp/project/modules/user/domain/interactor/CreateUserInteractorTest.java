package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.infrastructure.persistence.user.mappers.UserCreateMapper;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.exceptions.create.UserCreateValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserInteractorTest {

    @InjectMocks
    private CreateUserInteractor createUseInteractor;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserCreateMapper userCreateMapper;

    private UUID authorizedUserId;
    private UserCreateRequest userCreateRequest;
    private UserEntity authorizedUser;
    private UserEntity newUser;
    private UserCreateResponse userCreateResponse;

    @BeforeEach
    void setUp() {
        authorizedUserId = UUID.randomUUID();

        authorizedUser = new UserEntity(
                authorizedUserId,
                new Name("Admin User"),
                new Email("admin@example.com"),
                "password123",
                TypeUser.ADMIN,
                null, null, 0
        );

        userCreateRequest = new UserCreateRequest("New User", "newuser@example.com", "securepass");

        newUser = new UserEntity(
                UUID.randomUUID(),
                new Name("New User"),
                new Email("newuser@example.com"),
                "securepass",
                TypeUser.NORMAL,
                null, null, 0
        );

        userCreateResponse = new UserCreateResponse(newUser.getId(), newUser.getName().getValue(), newUser.getEmail().getValue());
    }

    @Test
    @DisplayName("Should create a user successfully when the user has permission")
    void shouldCreateUserSuccessfullyWhenAuthorized() {
        when(userRepository.findById(authorizedUserId)).thenReturn(Optional.of(authorizedUser));
        when(userCreateMapper.toEntity(userCreateRequest)).thenReturn(newUser);
        when(userCreateMapper.toResponse(newUser)).thenReturn(userCreateResponse);

        UserCreateResponse response = createUseInteractor.execute(userCreateRequest, authorizedUserId);

        verify(userRepository).findById(authorizedUserId);
        verify(userRepository).save(newUser);
        verify(userCreateMapper).toEntity(userCreateRequest);
        verify(userCreateMapper).toResponse(newUser);
        assertNotNull(response);
        assertEquals(userCreateResponse, response);
    }

    @Test
    @DisplayName("Should throw an exception when the user lacks permission to create another")
    void shouldThrowExceptionWhenUserLacksPermission() {
        UserEntity unauthorizedUser = new UserEntity(
                UUID.randomUUID(),
                new Name("Normal User"),
                new Email("normal@example.com"),
                "password",
                TypeUser.NORMAL,
                null, null, 0
        );
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(unauthorizedUser));

        assertThrows(UserCreateValidationException.class, () -> {
            createUseInteractor.execute(userCreateRequest, unauthorizedUser.getId());
        });

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should throw an exception when the authorized user is not found")
    void shouldThrowExceptionWhenAuthorizedUserNotFound() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundByIdException.class, () -> {
            createUseInteractor.execute(userCreateRequest, authorizedUserId);
        });

        verify(userRepository, never()).save(any(UserEntity.class));
    }
}