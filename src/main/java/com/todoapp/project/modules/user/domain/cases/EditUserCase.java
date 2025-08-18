package com.todoapp.project.modules.user.domain.cases;

public interface EditUserCase {

    UserEditResponse execute(UserEditRequest editRequest);
}
