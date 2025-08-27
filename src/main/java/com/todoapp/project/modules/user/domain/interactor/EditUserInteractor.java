package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.infrastructure.persistence.user.mappers.UserEditMapper;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.aplication.exception.UserNotFoundByIdException;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.EditUserCase;
import com.todoapp.project.modules.user.domain.exceptions.edit.UserEditValidationException;
import com.todoapp.project.modules.user.domain.port.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Interactor responsavel por editar a entidade do usuário
 *
 * <p>O interactor primeiro busca com o {@link UserRepository} as
 * entidades do usuário, tanto o que vai ser editado, quando o que
 * esta editando, posteriomente ele valida se o usuário pode editar
 * o outro usuário. Caso possa ele altera a entidade com base na
 * {@link UserEditRequest}. Após a classe {@link UserEditMapper}
 * mapear a entidade ele salva no banco de dados por via do {@link UserRepository}
 * e devolve o {@link UserEditResponse} com o {@link UserEditMapper}</p>
 *
 */
@Component
public class EditUserInteractor implements EditUserCase {

    private final UserRepository userRepository;
    private final UserEditMapper userEditMapper;


    /**
     * Cria uma nova instancia da classe EditUserInteractor
     * @param userRepository interface responsavel por puxar os usuários pelo banco de dados
     * @param userEditMapper classe responsavel por mapear a entidade usuário
     */
    public EditUserInteractor(UserRepository userRepository, UserEditMapper userEditMapper) {
        this.userRepository = userRepository;
        this.userEditMapper = userEditMapper;
    }

    /**
     * Executa o caso de uso de editar um usuário
     *
     * <p>Essa classe busca os usuários pelo banco de dados,
     * valida a permissão para editar. Caso tenha permissão,
     * transforma a entidade com base nas mudanças. Depois
     * disso ele mapeia a classe alterada para a resposta,
     * devolvendo ela</p>
     *
     * @param editRequest Request que irá conter as informações para editar
     * @param idExecuter Id do usuário que quer editar
     * @param idUser Id do usuário que será editado
     * @return UserEditResponse classe que contém as informações novas do usuário
     */
    @Transactional
    @Override
    public UserEditResponse execute(UserEditRequest editRequest, UUID idExecuter, UUID idUser) {
        UserEntity userExecuter = userRepository.findById(idExecuter).orElseThrow(() -> new UserNotFoundByIdException("User not found by id: " + idExecuter));;
        UserEntity userEdit = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundByIdException("User not found by id: " + idUser));

        validPermission(userEdit, userExecuter);

        userEditMapper.toEntity(editRequest, userEdit);

        userRepository.save(userEdit);

        return userEditMapper.toResponse(userEdit);
    }

    /**
     * Valida as permissões do usuário
     *
     * <p>Esse método verifica se o usuário são os mesmos,
     * caso seja ele retorna. Caso não seja, ele verifica
     * se o usuário não possui as permissões de editar
     * outro usuário, caso não tenha retorna uma exceção</p>
     *
     * @param userEdit Usuário que irá ser editado
     * @param userExecuter Usuário que irá editar
     * @throws UserEditValidationException lança exceção quando usuário não tiver permissão
     */
    private void validPermission(UserEntity userEdit, UserEntity userExecuter){
        if(!userExecuter.equals(userEdit) && !userExecuter.canEditUser()){
            throw new UserEditValidationException("User is not authorized to edit this profile.");
        }
    }
}
