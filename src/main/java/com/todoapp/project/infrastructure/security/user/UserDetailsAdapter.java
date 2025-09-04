package com.todoapp.project.infrastructure.security.user;

import com.todoapp.project.modules.user.domain.enumerator.TypeUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsAdapter implements UserDetails {

    private final long id;
    private final String name;
    private final String email;
    private final String password;
    private final TypeUser typeUser;

    public UserDetailsAdapter(long id, String name, String email, String password, TypeUser typeUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(typeUser);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    public long getId(){
        return id;
    }
}
