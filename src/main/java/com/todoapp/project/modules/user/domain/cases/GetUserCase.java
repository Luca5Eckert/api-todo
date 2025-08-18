package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;

public interface GetUserCase {

    UserGetResponse execute(UserGetRequest userGetRequest);
}
