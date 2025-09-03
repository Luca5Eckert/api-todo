package com.todoapp.project.infrastructure.api.service;

import com.todoapp.project.core.abstraction.UserAuthenticationService;
import com.todoapp.project.infrastructure.security.user.UserDetailsAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthenticationServiceAdapter implements UserAuthenticationService {

    @Override
    public UUID getIdUserAuthentication() {
        UserDetailsAdapter userDetails = (UserDetailsAdapter) getUserDetails();

        return userDetails.getId();
    }

    @Override
    public UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
