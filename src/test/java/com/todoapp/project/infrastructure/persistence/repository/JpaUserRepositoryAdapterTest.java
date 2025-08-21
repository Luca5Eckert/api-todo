package com.todoapp.project.infrastructure.persistence.user.repositorys;

import com.todoapp.project.modules.user.domain.UserEntity;
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

/**
 * Classe de teste unitário para JpaUserRepositoryAdapter.
 *
 * <p>O objetivo é garantir que o JpaUserRepositoryAdapter
 * delega corretamente as chamadas para a interface JpaUserRepository
 * real, sem testar a lógica de persistência em si.</p>
 */
@ExtendWith(MockitoExtension.class)
class JpaUserRepositoryAdapterTest {

    @Mock
    private JpaUserRepository jpaUserRepository;

    @InjectMocks
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    @Test
    @DisplayName("Deve retornar um Optional com o usuário quando o ID for encontrado")
    void findById_ShouldReturnOptionalUser_WhenIdExists() {
        // Cenário (Arrange)
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity(userId); // Use uma entidade mock real ou um mock

        // Define o comportamento do mock: quando findById for chamado, retorne um Optional com o usuário.
        when(jpaUserRepository.findById(userId)).thenReturn(Optional.of(user));

        // Ação (Act)
        Optional<UserEntity> result = jpaUserRepositoryAdapter.findById(userId);

        // Verificação (Assert)
        assertTrue(result.isPresent());
        assertEquals(user, result.get());

        // Garante que o método findById do mock foi chamado exatamente uma vez com o ID correto.
        verify(jpaUserRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Deve retornar um Optional vazio quando o ID não for encontrado")
    void findById_ShouldReturnEmptyOptional_WhenIdDoesNotExist() {
        // Cenário (Arrange)
        UUID userId = UUID.randomUUID();

        // Define o comportamento do mock: quando findById for chamado, retorne um Optional vazio.
        when(jpaUserRepository.findById(userId)).thenReturn(Optional.empty());

        // Ação (Act)
        Optional<UserEntity> result = jpaUserRepositoryAdapter.findById(userId);

        // Verificação (Assert)
        assertFalse(result.isPresent());

        // Garante que o método findById do mock foi chamado exatamente uma vez com o ID correto.
        verify(jpaUserRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Deve chamar o método save do JpaRepository quando save for invocado")
    void save_ShouldCallJpaRepositorySave() {
        // Cenário (Arrange)
        UserEntity user = new UserEntity();

        // Ação (Act)
        jpaUserRepositoryAdapter.save(user);

        // Verificação (Assert)
        // Garante que o método save do mock foi chamado exatamente uma vez com o objeto de usuário.
        verify(jpaUserRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Deve chamar o método deleteById do JpaRepository quando deleteById for invocado")
    void deleteById_ShouldCallJpaRepositoryDeleteById() {
        // Cenário (Arrange)
        UUID userId = UUID.randomUUID();

        // Ação (Act)
        jpaUserRepositoryAdapter.deleteById(userId);

        // Verificação (Assert)
        // Garante que o método deleteById do mock foi chamado exatamente uma vez com o ID correto.
        verify(jpaUserRepository, times(1)).deleteById(userId);
    }
}