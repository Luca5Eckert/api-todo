package com.todoapp.project.modules.furniture.domain.valueobject;

import com.todoapp.project.modules.furniture.domain.exception.description.FurnitureBlankDescriptionException;
import com.todoapp.project.modules.furniture.domain.exception.description.FurnitureLengthDescriptionException;

public class Description {

    private final String value;

    public Description(String value){
        if(value.isBlank()){
            throw new FurnitureBlankDescriptionException("Description can't be Blank");
        }
        if(value.length() > 400){
            throw new FurnitureLengthDescriptionException("Description can be max 400 words");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
