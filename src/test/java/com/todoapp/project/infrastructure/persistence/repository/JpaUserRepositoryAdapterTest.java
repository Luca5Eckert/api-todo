package com.todoapp.project.infrastructure.persistence.repository;

import com.todoapp.project.infrastructure.persistence.repositorys.JpaUserRepository;
import com.todoapp.project.infrastructure.persistence.repositorys.JpaUserRepositoryAdapter;
import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de teste para JpaUserRepositoryAdapter.
 * Testa o comportamento do adaptador que implementa a porta UserRepository,
 * garantindo que ele interaja corretamente com a interface JpaUserRepository.
 */
@ExtendWith(MockitoExtension.class)
class JpaUserRepositoryAdapterTest {

    // Mock da interface de repositório Spring Data JPA
    @Mock
    private JpaUserRepository jpaUserRepository;

    // Injeta o mock no adaptador que está sendo testado
    @InjectMocks
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    // Configuração inicial para cada teste
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve encontrar um usuário por ID com sucesso")
    void shouldFindUserByIdSuccessfully() {
        // Given
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        when(jpaUserRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // When
        UserEntity foundUser = jpaUserRepositoryAdapter.findById(userId);

        // Then
        assertNotNull(foundUser);
        assertEquals(userEntity, foundUser);
        verify(jpaUserRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o usuário não é encontrado por ID")
    void shouldThrowExceptionWhenUserIsNotFoundById() {
        // Given
        UUID userId = UUID.randomUUID();
        when(jpaUserRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UserNotFoundByIdException.class, () -> {
            jpaUserRepositoryAdapter.findById(userId);
        });
        verify(jpaUserRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Deve salvar um usuário com sucesso")
    void shouldSaveUserSuccessfully() {
        // Given
        UserEntity userEntityToSave = new UserEntity();
        when(jpaUserRepository.save(userEntityToSave)).thenReturn(userEntityToSave);

        // When
        jpaUserRepositoryAdapter.save(userEntityToSave);

        // Then
        verify(jpaUserRepository, times(1)).save(userEntityToSave);
    }
}

