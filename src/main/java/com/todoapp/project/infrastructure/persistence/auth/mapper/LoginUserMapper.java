package com.todoapp.project.infrastructure.persistence.auth.mapper;

import com.todoapp.project.infrastructure.security.user.UserDetailsAdapter;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginUserMapper {

    public UserLoginResponse toResponse(UserDetailsAdapter userDetailsAdapter, String token){
        return new UserLoginResponse(userDetailsAdapter.getId(), userDetailsAdapter.getUsername(), userDetailsAdapter.getEmail(), token);
    }


}
