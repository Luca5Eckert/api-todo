package com.todoapp.project.modules.user.domain.valueobjects;

import com.todoapp.project.modules.user.domain.exceptions.email.UserEmailBlankException;
import com.todoapp.project.modules.user.domain.exceptions.email.UserEmailSintaxeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private static final Pattern VALID_EMAIL = Pattern.compile("^(.+)@(.+)$");

    String value;

    public Email(String value){
        if(value.isBlank()){
            throw new UserEmailBlankException("User email can not be blank");
        }
        if(!isValid(value)){
            throw new UserEmailSintaxeException("Email sintaxe is invalid");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private boolean isValid(String value){
        Matcher matcher = VALID_EMAIL.matcher(value);
        return matcher.matches();
    }

}
