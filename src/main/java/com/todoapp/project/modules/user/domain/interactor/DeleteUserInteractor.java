package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.DeleteUserCase;
import com.todoapp.project.modules.user.domain.exceptions.delete.UserDeleteValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;

import java.util.UUID;

/**
 * Esse interactor é responsavel por deletar os usuários
 *
 * <p>Esse interador valida se o usuário que quer executar
 * tem permissão para deletar outro usuário antes de deletar
 * o usuário usando o {@link UserRepository}.</p>
 */
public class DeleteUserInteractor implements DeleteUserCase {

    public final UserRepository userRepository;

    /**
     * Cria um novo {@code DeleteUserInteractor}
     *
     * @param userRepository repository que serve para puxar e deletar usuários
     */
    public DeleteUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executa o caso de uso de deletar um usuário
     *
     * <p>Primeiro ela verifica se o usuário que está executando tem a
     * permissão de deletar outros usuários. Se a validação passar, ela
     * deleta o usuário especificado na requisição.</p>
     *
     * @param userDeleteRequest requisição contendo o Id do usuário
     * @param id O ID do usuário que está executando a ação
     * @throws UserDeleteValidationException se o usuário não tiver a permissão
     *
     */
    @Override
    public void execute(UserDeleteRequest userDeleteRequest, UUID id) {
        UserEntity userExecuter = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));;

        validUserPermissions(userExecuter);

        userRepository.deleteById(userDeleteRequest.id());
    }

    private void validUserPermissions(UserEntity userExecuter) {
        if(!userExecuter.canDeleteUser()){
            throw new UserDeleteValidationException("User can't delete another user");
        }
    }
}
