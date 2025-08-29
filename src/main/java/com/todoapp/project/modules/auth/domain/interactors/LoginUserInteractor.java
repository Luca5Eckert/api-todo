package com.todoapp.project.modules.auth.domain.interactors;

import com.todoapp.project.infrastructure.persistence.auth.mapper.LoginUserMapper;
import com.todoapp.project.infrastructure.provider.JwtTokenProvider;
import com.todoapp.project.infrastructure.security.user.UserDetailsAdapter;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginRequest;
import com.todoapp.project.modules.auth.aplication.dto.login.UserLoginResponse;
import com.todoapp.project.modules.auth.domain.cases.LoginUserCase;
import com.todoapp.project.modules.auth.domain.exception.AuthenticationValidationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class LoginUserInteractor implements LoginUserCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginUserMapper loginUserMapper;

    public LoginUserInteractor(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, LoginUserMapper loginUserMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginUserMapper = loginUserMapper;
    }

    @Override
    public UserLoginResponse execute(UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.email(), userLoginRequest.password()));

        if(!authentication.isAuthenticated()){
            throw new AuthenticationValidationException("Invalid credentials");
        }

        UserDetailsAdapter userDetails = (UserDetailsAdapter) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(userDetails.getUsername(), userDetails.getId().toString());

        return loginUserMapper.toResponse(userDetails, token);

    }

}
