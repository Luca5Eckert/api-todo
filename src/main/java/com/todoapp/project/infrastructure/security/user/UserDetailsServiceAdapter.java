package com.todoapp.project.infrastructure.security.user;

import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.exceptions.email.UserNotFoundByEmailException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsServiceAdapter implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;

    public UserDetailsServiceAdapter(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundByEmailException("User not found by email"));
        return userDetailsMapper.toDetails(userEntity);
    }
}
