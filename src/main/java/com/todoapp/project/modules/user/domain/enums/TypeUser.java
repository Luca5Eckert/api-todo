package com.todoapp.project.modules.user.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum TypeUser implements GrantedAuthority {

    ADMIN("ROLE_ADMIN"),
    NORMAL("ROLE_NORMAL");

    final String roleUser;

    TypeUser(String roleUser){
        this.roleUser = roleUser;
    }

    @Override
    public String getAuthority() {
        return roleUser;
    }
}
