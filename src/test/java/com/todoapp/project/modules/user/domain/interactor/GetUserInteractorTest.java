package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.infrastructure.persistence.user.mappers.UserGetMapper;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.port.UserRepository;
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

/**
 * Classe de testes unitários para {@link GetUserInteractor}.
 * Usa o JUnit 5 e o Mockito para testar a lógica do caso de uso
 * de forma isolada, simulando o comportamento das dependências.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para GetUserInteractor")
class GetUserInteractorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserGetMapper userGetMapper;

    @InjectMocks
    private GetUserInteractor getUserInteractor;

    private UUID userId;
    private UserGetRequest userGetRequest;
    private UserEntity userEntity;
    private UserGetResponse userGetResponse;

    /**
     * Configuração inicial para cada teste.
     * Define objetos de teste comuns para evitar repetição de código.
     */
    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        userGetRequest = new UserGetRequest(userId);
        userEntity = new UserEntity(userId, null, null, null, null, null, null, 0); // Simplificação, para o teste o importante é que seja um UserEntity.
        userGetResponse = new UserGetResponse(userId, "test@example.com", "Test User", TypeUser.NORMAL); // DTO de resposta esperado.
    }

    /**
     * Testa o cenário em que o usuário é encontrado com sucesso.
     */
    @Test
    @DisplayName("Deve retornar um DTO de usuário quando o ID é válido")
    void shouldReturnUserDtoWhenIdIsValid() {
        // Cenário (Arrange)
        // Simula o comportamento do repositório, retornando uma entidade de usuário.
        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(userEntity));
        // Simula o comportamento do mapper, convertendo a entidade para o DTO de resposta.
        when(userGetMapper.toResponse(userEntity)).thenReturn(userGetResponse);

        // Ação (Act)
        UserGetResponse result = getUserInteractor.execute(userGetRequest);

        // Verificação (Assert)
        // O resultado não deve ser nulo.
        assertNotNull(result, "O resultado não deve ser nulo.");
        // O DTO de resposta deve ser o esperado.
        assertEquals(userGetResponse.id(), result.id(), "O ID do DTO de resposta deve ser o mesmo do DTO esperado.");

        // Verificação de chamadas (Verify)
        // Confirma que o método findById do repositório foi chamado exatamente uma vez.
        verify(userRepository, times(1)).findById(userId);
        // Confirma que o método toResponse do mapper foi chamado exatamente uma vez.
        verify(userGetMapper, times(1)).toResponse(userEntity);
    }


}