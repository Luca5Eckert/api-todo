package com.todoapp.project.modules.user.domain;

import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id;

    private Name name;

    private String email;

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

    public UserEntity(UUID id, Name name, String email, String password, TypeUser type, LocalDateTime createAt, LocalDateTime updateAt, long version) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.version = version;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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




}
