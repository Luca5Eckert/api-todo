package com.todoapp.project.modules.user.domain.port;

import com.todoapp.project.modules.user.domain.UserEntity;

import java.util.UUID;

public interface UserRepository {

    UserEntity findById(UUID id);

    void save(UserEntity userEntity);

    void deleteById(UUID id);

}
