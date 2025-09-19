package com.todoapp.project.infrastructure.persistence.furniture.repository;

import com.todoapp.project.modules.furniture.domain.FurnitureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFurnitureRepository extends JpaRepository<FurnitureEntity, Long> {

}
