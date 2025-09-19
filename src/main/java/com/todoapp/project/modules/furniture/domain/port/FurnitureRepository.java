package com.todoapp.project.modules.furniture.domain.port;

import com.todoapp.project.modules.furniture.domain.FurnitureEntity;

public interface FurnitureRepository {

    void save(FurnitureEntity furniture);

}
