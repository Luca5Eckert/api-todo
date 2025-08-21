package com.todoapp.project.modules.user.domain.port;

import com.todoapp.project.modules.user.domain.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<UserEntity> findById(UUID id);

    void save(UserEntity userEntity);

    void deleteById(UUID id);

}
