package com.todoapp.project.modules.user.domain.cases;

import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;

/**
 * Interface para o caso de uso de obtenção de usuário.
 *
 * <p>Define o contrato para a operação de buscar e recuperar
 * informações de um usuário existente.</p>
 */
public interface GetUserCase {

    /**
     * Executa o caso de uso para obter um usuário.
     *
     * @param userGetRequest um objeto {@link UserGetRequest} contendo o ID do usuário a ser buscado.
     * @return um objeto {@link UserGetResponse} com os detalhes do usuário encontrado.
     */
    UserGetResponse execute(UserGetRequest userGetRequest);
}