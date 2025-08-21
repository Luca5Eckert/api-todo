package com.todoapp.project.infrastructure.persistence.user.mappers;

import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.enums.TypeUser;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserGetMapperTest {

    @InjectMocks
    private UserGetMapper userGetMapper;

    @Test
    public void shouldReturnAUserGetResponse(){
        UserEntity userEntity = new UserEntity(UUID.randomUUID(), new Name("Lucas"), new Email("Lucas@gmail.com"), "lucas lucas", TypeUser.NORMAL, LocalDateTime.now(), LocalDateTime.now(), 1);
        UserGetResponse userGetResponse = new UserGetResponse(userEntity.getId(), userEntity.getName().getValue(), userEntity.getEmail().getValue(), userEntity.getType());

        UserGetResponse userGetResponseTest = userGetMapper.toResponse(userEntity);

        assertEquals(userGetResponse, userGetResponseTest);
    }


}