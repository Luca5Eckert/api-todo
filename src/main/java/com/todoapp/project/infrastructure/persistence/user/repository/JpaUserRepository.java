package com.todoapp.project.infrastructure.persistence.user.repository;

import com.todoapp.project.modules.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID>{
}
