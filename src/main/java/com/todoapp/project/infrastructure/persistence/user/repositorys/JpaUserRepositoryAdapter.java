package com.todoapp.project.infrastructure.persistence.user.repositorys;

import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class JpaUserRepositoryAdapter implements UserRepository {

    private JpaUserRepository jpaUserRepository;

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public void save(UserEntity userEntity) {
        jpaUserRepository.save(userEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }
}
