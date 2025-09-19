package com.todoapp.project.modules.furniture.aplication.port;

import com.todoapp.project.modules.furniture.aplication.dto.FurnitureCreateRequest;

public interface FurnitureService {

    void create(FurnitureCreateRequest furnitureCreateRequest);
}
