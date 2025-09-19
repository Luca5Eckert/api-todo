package com.todoapp.project.modules.furniture.domain.mapper;

import com.todoapp.project.modules.furniture.aplication.dto.FurnitureCreateRequest;
import com.todoapp.project.modules.furniture.domain.FurnitureEntity;
import com.todoapp.project.modules.furniture.domain.valueobject.Description;
import com.todoapp.project.modules.user.domain.valueobjects.Name;

public class FurnitureMapper {

    public FurnitureEntity toEntity(FurnitureCreateRequest furnitureCreateRequest) {
        FurnitureEntity furniture = new FurnitureEntity();

        furniture.setName(new Name(furnitureCreateRequest.name()));
        furniture.setDescription(new Description(furnitureCreateRequest.description()));
        furniture.setDateStartToCreate(furnitureCreateRequest.dateStartToCreate());
        furniture.setEstimeDateToFinish(furnitureCreateRequest.estimeDateToFinish());

        return furniture;
    }

}
