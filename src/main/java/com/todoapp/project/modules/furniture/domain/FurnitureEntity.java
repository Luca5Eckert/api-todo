package com.todoapp.project.modules.furniture.domain;

import com.todoapp.project.infrastructure.persistence.user.converter.NameConverter;
import com.todoapp.project.modules.furniture.domain.enumerator.TypeFurniture;
import com.todoapp.project.modules.furniture.domain.exception.description.FurnitureBlankDescriptionException;
import com.todoapp.project.modules.furniture.domain.exception.description.FurnitureDescriptionException;
import com.todoapp.project.modules.furniture.domain.exception.description.FurnitureLengthDescriptionException;
import com.todoapp.project.modules.furniture.domain.exception.estimedate.FurnitureEstimeDateException;
import com.todoapp.project.modules.furniture.domain.exception.startdate.FurnitureStartDateException;
import com.todoapp.project.modules.furniture.domain.exception.type.FurnitureTypeException;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class FurnitureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;

    @Convert(converter = NameConverter.class)
    private Name name;

    private String description;

    private TypeFurniture type;

    @CreationTimestamp
    private final LocalDateTime dateCreateEntity;

    private LocalDateTime dateStartToCreate;

    private LocalDateTime estimeDateToFinish;

    public FurnitureEntity() {
        this.id = -1;
        this.dateCreateEntity = null;
    }

    public FurnitureEntity(Name name, String description, TypeFurniture type, LocalDateTime dateStartToCreate, LocalDateTime estimeDateToFinish){
        this.id = -1;
        this.type = type;
        this.name = name;
        this.description = description;
        this.dateCreateEntity = null;
        this.dateStartToCreate = dateStartToCreate;
        this.estimeDateToFinish = estimeDateToFinish;
    }

    public long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.isBlank()){
            throw new FurnitureBlankDescriptionException("Description can't be Blank");
        }
        if(description.length() > 400){
            throw new FurnitureLengthDescriptionException("Description can be max 400 words");
        }

        this.description = description;
    }

    public TypeFurniture getType() {
        return type;
    }

    public void setType(TypeFurniture type) {
        if(type == null){
            throw new FurnitureTypeException("Type can't be null");
        }
        this.type = type;
    }

    public LocalDateTime getDateCreateEntity() {
        return dateCreateEntity;
    }

    public LocalDateTime getDateStartToCreate() {
        return dateStartToCreate;
    }

    public void setDateStartToCreate(LocalDateTime dateStartToCreate) {
        if(dateStartToCreate.isBefore(this.estimeDateToFinish)){
            throw new FurnitureStartDateException("The start date can't be before then estime date");
        }
        this.dateStartToCreate = dateStartToCreate;
    }

    public LocalDateTime getEstimeDateToFinish() {
        return estimeDateToFinish;
    }

    public void setEstimeDateToFinish(LocalDateTime estimeDateToFinish) {
        if(estimeDateToFinish.isAfter(this.dateStartToCreate)){
            throw new FurnitureEstimeDateException("The estime date can't be after then start date");
        }
        this.estimeDateToFinish = estimeDateToFinish;
    }

}
