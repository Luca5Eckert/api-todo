package com.todoapp.project.modules.user.domain.valueobjects;

import com.todoapp.project.modules.user.domain.exceptions.UserNameBlankException;

public class Name {

    private String value;

    public Name(String value){
        if(value.isBlank()){
            throw new UserNameBlankException("User name can not be blank");
        }
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if(value.isBlank()){
            throw new UserNameBlankException("User name can not be blank");
        }
        this.value = value;
    }
}
