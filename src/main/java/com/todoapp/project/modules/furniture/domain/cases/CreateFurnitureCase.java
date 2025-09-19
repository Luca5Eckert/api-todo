package com.todoapp.project.modules.furniture.domain.cases;

import com.todoapp.project.modules.furniture.aplication.dto.FurnitureCreateRequest;
import com.todoapp.project.modules.furniture.domain.FurnitureEntity;

public interface CreateFurnitureCase {

    void execute(FurnitureEntity furniture);

}
