package com.todoapp.project.infrastructure.persistence.furniture.repository;

import com.todoapp.project.modules.furniture.domain.FurnitureEntity;
import com.todoapp.project.modules.furniture.domain.port.FurnitureRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FurnitureRepositoryAdapter implements FurnitureRepository {

    private final JpaFurnitureRepository jpaFurnitureRepository;

    public FurnitureRepositoryAdapter(JpaFurnitureRepository jpaFurnitureRepository) {
        this.jpaFurnitureRepository = jpaFurnitureRepository;
    }

    @Override
    public void save(FurnitureEntity furniture) {
        jpaFurnitureRepository.save(furniture);
    }

}
