package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.infrastructure.persistence.user.mappers.UserCreateMapper;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for the CreateUseInteractor class.
 * This class uses JUnit 5 and Mockito to test the behavior of the interactor
 * in different scenarios, such as successful creation and permission validation failures.
 */
@ExtendWith(MockitoExtension.class)
class CreateUserInteractorTest {

    // Instance of the class we want to test, with the mocked dependencies injected.
    @InjectMocks
    private CreateUserInteractor createUseInteractor;

    // Mock of the dependencies of the CreateUseInteractor class.
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserCreateMapper userCreateMapper;

    // Test IDs and objects
    private UUID authorizedUserId;
    private UserCreateRequest userCreateRequest;
    private UserEntity authorizedUser;
    private UserEntity newUser;
    private UserCreateResponse userCreateResponse;

    /**
     * Initial setup for each test.
     * Creates mocked test objects to simulate the scenario.
     */
    @BeforeEach
    void setUp() {
        authorizedUserId = UUID.randomUUID();

        // Simulates a user with permission (ADMIN) to create other users.
        authorizedUser = new UserEntity(
                authorizedUserId,
                new Name("Admin User"),
                new Email("admin@example.com"),
                "password123",
                TypeUser.ADMIN,
                null, null, 0
        );

        // Simulates the request to create a new user.
        userCreateRequest = new UserCreateRequest("New User", "newuser@example.com", "securepass");

        // Simulates the user entity that would be saved in the database.
        // The ID is generated, and the type is NORMAL by default, as defined in the interactor.
        newUser = new UserEntity(
                UUID.randomUUID(),
                new Name("New User"),
                new Email("newuser@example.com"),
                "securepass",
                TypeUser.NORMAL,
                null, null, 0
        );

        // Simulates the response that would be returned after creation.
        userCreateResponse = new UserCreateResponse(newUser.getId(), newUser.getName().getValue(), newUser.getEmail().getValue());
    }

    @Test
    @DisplayName("Should create a user successfully when the user has permission")
    void shouldCreateUserSuccessfullyWhenAuthorized() {
        // Scenario:
        // 1. userRepository.findById returns the authorized user (ADMIN).
        // 2. userCreateMapper.toEntity converts the request to the entity.
        // 3. userRepository.save is called.
        // 4. userCreateMapper.toResponse converts the entity to the response.
        when(userRepository.findById(authorizedUserId)).thenReturn(authorizedUser);
        when(userCreateMapper.toEntity(userCreateRequest)).thenReturn(newUser);
        when(userCreateMapper.toResponse(newUser)).thenReturn(userCreateResponse);

        // Action: Calls the createUser method.
        UserCreateResponse response = createUseInteractor.createUser(userCreateRequest, authorizedUserId);

        // Verification:
        // 1. The findById method was called with the correct ID.
        // 2. The save method was called once with the new user.
        // 3. The returned response is as expected.
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
        // Scenario:
        // 1. userRepository.findById returns a user without permission (NORMAL).
        UserEntity unauthorizedUser = new UserEntity(
                UUID.randomUUID(),
                new Name("Normal User"),
                new Email("normal@example.com"),
                "password",
                TypeUser.NORMAL,
                null, null, 0
        );
        when(userRepository.findById(any(UUID.class))).thenReturn(unauthorizedUser);

        // Action and Verification:
        // Ensures that the 'createUser' method throws the expected exception.
        assertThrows(UserCreateValidationException.class, () -> {
            createUseInteractor.createUser(userCreateRequest, unauthorizedUser.getId());
        });

        // Verifies that the 'save' method was NEVER called, because the validation failed beforehand.
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should throw an exception when the authorized user is not found")
    void shouldThrowExceptionWhenAuthorizedUserNotFound() {
        // Scenario:
        // 1. userRepository.findById returns null.
        when(userRepository.findById(any(UUID.class))).thenReturn(null);

        // Action and Verification:
        // The logic assumes that 'user.canCreateUser()' will throw a NullPointerException.
        // The test verifies that this exception is thrown.
        assertThrows(NullPointerException.class, () -> {
            createUseInteractor.createUser(userCreateRequest, authorizedUserId);
        });

        // Verifies that the 'save' method was NEVER called.
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
