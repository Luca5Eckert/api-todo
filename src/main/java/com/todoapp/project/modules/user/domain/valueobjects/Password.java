package com.todoapp.project.modules.user.domain.valueobjects;

import com.todoapp.project.modules.user.domain.exceptions.password.PasswordValidException;
import com.todoapp.project.modules.user.domain.validator.PasswordValidator;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {

    private final String value;

    private Password(String value){
        this.value = value;
    }

    public static Password fromPlain(String raw, PasswordEncoder encoder, PasswordValidator validator) {
        if (!validator.valid(raw)) {
            throw new PasswordValidException("Password is not strong enough");
        }
        return new Password(encoder.encode(raw));
    }

    public static Password fromHash(String hash) {
        return new Password(hash);
    }

    public boolean matches(String raw, PasswordEncoder encoder) {
        return encoder.matches(raw, value);
    }

    public String raw() {
        return value;
    }

    @Override
    public String toString() {
        return "*********";
    }
}
