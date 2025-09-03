package com.todoapp.project.modules.user.domain.port;

import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.valueobjects.Email;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<UserEntity> findById(long id);

    void save(UserEntity userEntity);

    void deleteById(long id);

    Optional<UserEntity> findByEmail(Email email);

}
