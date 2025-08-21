package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.exceptions.delete.UserDeleteValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteUserInteractorTest {

    private UserRepository userRepository;
    private DeleteUserInteractor deleteUserInteractor;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        deleteUserInteractor = new DeleteUserInteractor(userRepository);
    }

    @Test
    void shouldDeleteUserWhenExecutorHasPermission() {
        // Arrange
        UUID executerId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();

        UserEntity executer = mock(UserEntity.class);
        when(executer.canDeleteUser()).thenReturn(true);
        when(userRepository.findById(executerId)).thenReturn(Optional.of(executer));

        UserDeleteRequest request = new UserDeleteRequest(targetId);

        // Act
        deleteUserInteractor.execute(request, executerId);

        // Assert
        verify(userRepository).deleteById(targetId);
    }

    @Test
    void shouldThrowExceptionWhenExecutorDoesNotHavePermission() {
        // Arrange
        UUID executerId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();

        UserEntity executer = mock(UserEntity.class);
        when(executer.canDeleteUser()).thenReturn(false);
        when(userRepository.findById(executerId)).thenReturn(Optional.of(executer));

        UserDeleteRequest request = new UserDeleteRequest(targetId);

        // Act & Assert
        assertThrows(UserDeleteValidationException.class,
                () -> deleteUserInteractor.execute(request, executerId));

        verify(userRepository, never()).deleteById(any());
    }
}