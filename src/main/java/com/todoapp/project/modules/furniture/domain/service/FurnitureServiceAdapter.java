package com.todoapp.project.modules.furniture.domain.service;

import com.todoapp.project.modules.furniture.aplication.dto.FurnitureCreateRequest;
import com.todoapp.project.modules.furniture.aplication.port.FurnitureService;
import com.todoapp.project.modules.furniture.domain.cases.CreateFurnitureCase;
import com.todoapp.project.modules.furniture.domain.mapper.FurnitureMapper;
import org.springframework.stereotype.Service;

@Service
public class FurnitureServiceAdapter implements FurnitureService {

    private final FurnitureMapper furnitureMapper;

    private final CreateFurnitureCase createFurnitureCase;

    public FurnitureServiceAdapter(FurnitureMapper furnitureMapper, CreateFurnitureCase createFurnitureCase) {
        this.furnitureMapper = furnitureMapper;
        this.createFurnitureCase = createFurnitureCase;
    }

    @Override
    public void create(FurnitureCreateRequest furnitureCreateRequest) {
        var furniture = furnitureMapper.toEntity(furnitureCreateRequest);

        createFurnitureCase.execute(furniture);
    }

}
