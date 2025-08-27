package com.todoapp.project.modules.user.domain.service;

import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.aplication.port.UserService;
import com.todoapp.project.modules.user.domain.cases.CreateUserCase;
import com.todoapp.project.modules.user.domain.cases.DeleteUserCase;
import com.todoapp.project.modules.user.domain.cases.EditUserCase;
import com.todoapp.project.modules.user.domain.cases.GetUserCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service responsável pelas principais operações do usuário
 *
 * <p>Classe que realiza as quatro operações basicas utilizando os
 * cases criados. Ela cria usuário se utilizando do {@link CreateUserCase},
 * ela pega o usuário utilizando o {@link GetUserCase}, ela apaga utilizando
 * o {@link DeleteUserCase} e ela edita utilizando o {@link EditUserCase}</p>
 *
 */
@Service
public class UserSeviceImpl implements UserService {

    private final CreateUserCase createUserCase;
    private final EditUserCase editUserCase;
    private final DeleteUserCase deleteUserCase;
    private final GetUserCase getUserCase;

    /**
     * Cria uma instancia da classe {@code UserServiceImpl}
     *
     * @param createUserCase classe responsável por adicionar usuário
     * @param editUserCase classe responsável por editar usuário
     * @param deleteUserCase classe responsável por deletar o usuário
     * @param getUserCase classe responsável por pegar usuário
     */
    public UserSeviceImpl(CreateUserCase createUserCase, EditUserCase editUserCase, DeleteUserCase deleteUserCase, GetUserCase getUserCase) {
        this.createUserCase = createUserCase;
        this.editUserCase = editUserCase;
        this.deleteUserCase = deleteUserCase;
        this.getUserCase = getUserCase;
    }

    /**
     * Cria usuário
     *
     * <p>Esse método delega a criação dousuário para o caso de uso
     * {@link CreateUserCase}</p>
     *
     * @param userCreateRequest Classe que possui as informações para
     * adicionar usuário
     * @param id id do usuário que irá adicionar outro usuário
     * @return UserCreateResponse classe com as informações do usuário criado
     *
     */
    @Override
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest, UUID id) {
        return createUserCase.execute(userCreateRequest, id);
    }

    /**
     * Busca o usuário
     *
     * <p>Esse método delega a busca o usuário para o
     * caso de uso{@link GetUserCase}
     *
     * @param userGetRequest Classe responsável por armazenar as informações
     *                       para buscar o usuário
     * @return UserGetResponse Devolve uma classe com as informações do usuário coletadas
     */
    @Override
    public UserGetResponse getUser(UserGetRequest userGetRequest){
        return getUserCase.execute(userGetRequest);
    }

    /**
     * Deleta usuário
     *
     * <p>Esse método delega a apagar do usuário para o
     * caso de uso {@link DeleteUserCase}</p>
     *
     * @param deleteRequest Classe responsável por armazenar as informações
     *                      necessarias para deletar um usuário
     * @param id id do usuário que vai deletar o outro usuário
     */
    @Override
    public void deleteUser(UserDeleteRequest deleteRequest, UUID id){
        deleteUserCase.execute(deleteRequest, id);
    }


    /**
     * Edita/Altera um usuário
     *
     * <p>Esse método delega a edição do usuário para
     * a caso de uso {@link EditUserCase}</p>
     *
     * @param userEditRequest Classe responsável por armazenas as
     *                        informações que serão alteradas
     * @param idUser id do usuário que vai ser alterado
     * @param idExecuter id do usuário que irá editar
     * @return UserEditResponse classe com as informações do usuário
     * alteradas
     */
    @Override
    public UserEditResponse editResponse(UserEditRequest userEditRequest, UUID idUser, UUID idExecuter){
        return editUserCase.execute(userEditRequest, idExecuter, idUser);
    }



}
