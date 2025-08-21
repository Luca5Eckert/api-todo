package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;

import java.util.UUID;


/**
 * Interface para o caso de uso de editar um usuário
 *
 * <p>Define o contrato o caso de uso de editar um usuário</p>
 */
public interface EditUserCase {


    /**
     * Executa o caso de uso de editar o usuário
     *
     * @param editRequest classe que contém todos os valores a serem editados
     * @param idExecuter id do usuário que irá executar a ação
     * @param idUser id do usuário que será editado
     * @return UserEditResponse retorna uma objeto com as informações do usuário alteradas
     */
    UserEditResponse execute(UserEditRequest editRequest, UUID idExecuter, UUID idUser);
}
