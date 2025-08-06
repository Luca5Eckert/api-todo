package com.todoapp.project.infrastructure.persistence.repositorys;

import com.todoapp.project.modules.user.domain.port.UserRepository;

public class JpaUserRepositoryAdapter implements UserRepository {

    private JpaUserRepository jpaUserRepository;
}
