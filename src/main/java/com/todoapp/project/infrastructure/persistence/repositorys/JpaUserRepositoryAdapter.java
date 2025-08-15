package com.todoapp.project.infrastructure.persistence.repositorys;

import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JpaUserRepositoryAdapter implements UserRepository {

    private JpaUserRepository jpaUserRepository;

    @Override
    public UserEntity findById(UUID id) {
        return jpaUserRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));
    }

    @Override
    public void save(UserEntity userEntity) {
        jpaUserRepository.save(userEntity);
    }
}
