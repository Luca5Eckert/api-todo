package com.todoapp.project.infrastructure.security.user;

import com.todoapp.project.modules.user.domain.UserEntity;

public class UserDetailsMapper {

    public UserDetailsAdapter toDetails(UserEntity userEntity){
        return new UserDetailsAdapter(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue(), userEntity.getPassword(), userEntity.getType());
    }
}
