package com.todoapp.project.modules.furniture.aplication.controller;

import com.todoapp.project.infrastructure.api.dto.ApiResponseDto;
import com.todoapp.project.modules.furniture.aplication.dto.FurnitureCreateRequest;
import com.todoapp.project.modules.furniture.aplication.port.FurnitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/movelapp/furniture")
@RestController
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto<Void>> create(FurnitureCreateRequest furnitureCreateRequest){
        furnitureService.create(furnitureCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(201, "Furniture created with success"));
    }


}
