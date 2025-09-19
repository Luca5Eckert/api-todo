package com.todoapp.project.modules.furniture.aplication.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record FurnitureCreateRequest(String name, String description, OffsetDateTime dateStartToCreate, OffsetDateTime estimeDateToFinish) {
}
