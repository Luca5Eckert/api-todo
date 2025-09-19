package com.todoapp.project.modules.furniture.domain.interactor;

import com.todoapp.project.modules.furniture.aplication.dto.FurnitureCreateRequest;
import com.todoapp.project.modules.furniture.domain.FurnitureEntity;
import com.todoapp.project.modules.furniture.domain.cases.CreateFurnitureCase;
import com.todoapp.project.modules.furniture.domain.port.FurnitureRepository;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateFurnitureInteractor implements CreateFurnitureCase {

    private final FurnitureRepository furnitureRepository;

    public CreateFurnitureInteractor(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public void execute(FurnitureEntity furniture) {

        furnitureRepository.save(furniture);

    }

}
