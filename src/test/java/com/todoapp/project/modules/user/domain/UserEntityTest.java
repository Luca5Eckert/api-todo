package com.todoapp.project.modules.user.domain;


import com.todoapp.project.modules.user.domain.enumerator.TypeUser;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import com.todoapp.project.modules.user.domain.valueobjects.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the UserEntity entity.
 * This class uses JUnit 5 to validate the behavior of the entity,
 * including constructors, getters, setters, and the canCreateUser() method logic.
 */
class UserEntityTest {

    @Test
    @DisplayName("Should construct UserEntity with the full constructor and verify all fields")
    void shouldConstructUserEntityWithFullConstructorAndVerifyFields() {
        // Given - Data to create the object
        long id = 234234;
        Name name = new Name("John Doe");
        Email email = new Email("john.doe@example.com");
        Password password = Password.fromHash("securePassword123");
        TypeUser type = TypeUser.ADMIN;
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime updateAt = LocalDateTime.now();
        long version = 1L;

        // When - Action of instantiating the object
        UserEntity user = new UserEntity(id, name, email, password, type, createAt, updateAt, version);

        // Then - Verification
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(type, user.getType());
        assertEquals(createAt, user.getCreateAt());
        assertEquals(updateAt, user.getUpdateAt());
        assertEquals(version, user.getVersion());
    }

    @Test
    @DisplayName("Should construct UserEntity with the simplified constructor and verify generated fields")
    void shouldConstructUserEntityWithSimplifiedConstructorAndVerifyGeneratedFields() {
        // Given
        Name name = new Name("Jane Smith");
        Email email = new Email("jane.smith@example.com");
        Password password = Password.fromHash("anotherSecurePassword");
        TypeUser type = TypeUser.NORMAL;

        // When
        UserEntity user = new UserEntity(name, email, password, type);

        // Then
        assertNotNull(user);
        assertEquals(user.getId(), -1);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(type, user.getType());
        assertNotNull(user.getCreateAt());
        assertNotNull(user.getUpdateAt());
    }

    @Test
    @DisplayName("Should construct UserEntity with the empty constructor and verify null fields")
    void shouldConstructUserEntityWithEmptyConstructorAndVerifyNullFields() {
        // When
        UserEntity user = new UserEntity();

        // Then
        assertNotNull(user);
        assertEquals(user.getId(), -1);
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getType());
        assertNull(user.getCreateAt());
        assertNull(user.getUpdateAt());
        assertEquals(-1, user.getVersion());
    }

    @Test
    @DisplayName("Should allow user creation for an ADMIN")
    void shouldAllowUserCreationForAdmin() {
        // Given
        UserEntity adminUser = new UserEntity(new Name("Admin"), new Email("admin@test.com"), Password.fromHash("pass"), TypeUser.ADMIN);

        // When & Then
        assertTrue(adminUser.canCreateUser(), "An ADMIN user should be able to create other users.");
    }

    @Test
    @DisplayName("Should not allow user creation for a NORMAL user")
    void shouldNotAllowUserCreationForNormal() {
        // Given
        UserEntity normalUser = new UserEntity(new Name("Normal"), new Email("normal@test.com"), Password.fromHash("pass"), TypeUser.NORMAL);

        // When & Then
        assertFalse(normalUser.canCreateUser(), "A NORMAL user should not be able to create other users.");
    }

    @Test
    @DisplayName("Should update the name using the setter")
    void shouldUpdateNameUsingSetter() {
        // Given
        UserEntity user = new UserEntity(new Name("Old Name"), new Email("user@test.com"), Password.fromHash("pass"), TypeUser.NORMAL);
        Name newName = new Name("New Name");

        // When
        user.setName(newName);

        // Then
        assertEquals(newName, user.getName());
    }

    @Test
    @DisplayName("Should update the email using the setter")
    void shouldUpdateEmailUsingSetter() {
        // Given
        UserEntity user = new UserEntity(new Name("User"), new Email("old@test.com"), Password.fromHash("pass"), TypeUser.NORMAL);
        Email newEmail = new Email("new@test.com");

        // When
        user.setEmail(newEmail);

        // Then
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    @DisplayName("Should update the password using the setter")
    void shouldUpdatePasswordUsingSetter() {
        // Given
        UserEntity user = new UserEntity(new Name("User"), new Email("user@test.com"), Password.fromHash("oldpass"), TypeUser.NORMAL);
        Password newPassword = Password.fromHash("newPassword123");

        // When
        user.setPassword(newPassword);

        // Then
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    @DisplayName("Should update the user type using the setter")
    void shouldUpdateUserTypeUsingSetter() {
        // Given
        UserEntity user = new UserEntity(new Name("User"), new Email("user@test.com"), Password.fromHash("pass"), TypeUser.NORMAL);
        TypeUser newType = TypeUser.ADMIN;

        // When
        user.setType(newType);

        // Then
        assertEquals(newType, user.getType());
    }
}