package com.todoapp.project.infrastructure.persistence.user.mappers;

import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.enumerator.TypeUser;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import com.todoapp.project.modules.user.domain.valueobjects.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserEditMapperTest {

    private UserEditMapper userEditMapper;

    @BeforeEach
    void setUp() {
        userEditMapper = new UserEditMapper();
    }

    @Test
    @DisplayName("Should update UserEntity with data from UserEditRequest")
    void toEntity_shouldUpdateUserEntityCorrectly() {
        // Cenário (Arrange)
        UserEditRequest request = new UserEditRequest("New Name", "new.email@example.com");
        UserEntity userToUpdate = new UserEntity(
                64564,
                new Name("Old Name"),
                new Email("old.email@example.com"),
                Password.fromHash("lucas lucas"),
                TypeUser.NORMAL,
                null, null, 0
        );

        // Ação (Act)
        userEditMapper.toEntity(request, userToUpdate);

        // Verificação (Assert)
        assertEquals("New Name", userToUpdate.getName().getValue());
        assertEquals("new.email@example.com", userToUpdate.getEmail().getValue());
    }

    @Test
    @DisplayName("Should map UserEntity to UserEditResponse")
    void toResponse_shouldMapUserEntityToResponseCorrectly() {
        // Cenário (Arrange)
        long userId = 4645;
        UserEntity userEntity = new UserEntity(
                userId,
                new Name("Test User"),
                new Email("test@example.com"),
                Password.fromHash("lucas lucas"),
                TypeUser.ADMIN,
                null, null, 0
        );

        // Ação (Act)
        UserEditResponse response = userEditMapper.toResponse(userEntity);

        // Verificação (Assert)
        assertNotNull(response);
        assertEquals(userId, response.id());
        assertEquals("Test User", response.name());
        assertEquals("test@example.com", response.email());
    }
}