package com.todoapp.project.modules.furniture.domain;

import com.todoapp.project.infrastructure.persistence.furniture.converter.DescriptionConverter;
import com.todoapp.project.infrastructure.persistence.user.converter.NameConverter;
import com.todoapp.project.modules.furniture.domain.enumerator.TypeFurniture;
import com.todoapp.project.modules.furniture.domain.exception.description.FurnitureDescriptionException;
import com.todoapp.project.modules.furniture.domain.exception.estimedate.FurnitureEstimeDateException;
import com.todoapp.project.modules.furniture.domain.exception.name.FurnitureNameException;
import com.todoapp.project.modules.furniture.domain.exception.startdate.FurnitureStartDateException;
import com.todoapp.project.modules.furniture.domain.exception.type.FurnitureTypeException;
import com.todoapp.project.modules.furniture.domain.valueobject.Description;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
public class FurnitureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;

    @Convert(converter = NameConverter.class)
    private Name name;

    @Convert(converter = DescriptionConverter.class)
    private Description description;

    private TypeFurniture type;

    @CreationTimestamp
    private final LocalDateTime dateCreateEntity;

    private OffsetDateTime dateStartToCreate;

    private OffsetDateTime estimeDateToFinish;

    public FurnitureEntity() {
        this.id = -1;
        this.dateCreateEntity = null;
    }

    public FurnitureEntity(Name name, Description description, TypeFurniture type, OffsetDateTime  dateStartToCreate, OffsetDateTime  estimeDateToFinish){
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
        if(name == null){
            throw new FurnitureNameException("The name can't be null");
        }
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        if(description == null){
            throw new FurnitureDescriptionException("The description can't be null");
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

    public OffsetDateTime getDateStartToCreate() {
        return dateStartToCreate;
    }

    public void setDateStartToCreate(OffsetDateTime dateStartToCreate) {
        if(dateStartToCreate == null){
            throw new FurnitureEstimeDateException("The start date can't be null");
        }
        if(dateStartToCreate.isBefore(this.estimeDateToFinish)){
            throw new FurnitureStartDateException("The start date can't be before then estime date");
        }
        this.dateStartToCreate = dateStartToCreate;
    }

    public OffsetDateTime getEstimeDateToFinish() {
        return estimeDateToFinish;
    }

    public void setEstimeDateToFinish(OffsetDateTime estimeDateToFinish) {
        if(estimeDateToFinish == null){
            throw new FurnitureEstimeDateException("The estime date can't be null");
        }
        if(estimeDateToFinish.isAfter(this.dateStartToCreate)){
            throw new FurnitureEstimeDateException("The estime date can't be after then start date");
        }
        this.estimeDateToFinish = estimeDateToFinish;
    }

}
