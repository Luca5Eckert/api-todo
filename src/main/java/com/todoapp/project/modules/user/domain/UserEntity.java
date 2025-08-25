package com.todoapp.project.modules.user.domain;

import com.todoapp.project.infrastructure.persistence.user.converter.EmailConverter;
import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id;

    private Name name;

    @Convert(converter = EmailConverter.class)
    private Email email;

    private String password;

    private TypeUser type;

    @CreatedDate
    private final LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Version
    private long version;


    public UserEntity() {
        this.id = null;
        this.name = null;
        this.email = null;
        this.password = null;
        this.type = null;
        this.createAt = null;
        this.updateAt = null;
        this.version = -1;
    }

    public UserEntity(UUID id, Name name, Email email, String password, TypeUser type, LocalDateTime createAt, LocalDateTime updateAt, long version) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.version = version;
    }


    public UserEntity(Name name, Email email, String password, TypeUser typeUser) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = typeUser;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public UserEntity(UUID userId) {
        this.id = userId;
        this.createAt = null;
    }



    public boolean canCreateUser() {
        return switch (type){
            case ADMIN -> true;
            default -> false;
        };
    }

    public boolean canDeleteUser() {
        return switch (type){
            case ADMIN -> true;
            default -> false;
        };

    }

    public boolean canEditUser() {
        return switch (type){
            case ADMIN -> true;
            default -> false;
        };
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return version == user.version && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && type == user.type && Objects.equals(createAt, user.createAt) && Objects.equals(updateAt, user.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, type, createAt, updateAt, version);
    }
}
