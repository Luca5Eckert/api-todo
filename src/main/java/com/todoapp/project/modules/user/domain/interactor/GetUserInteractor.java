package com.todoapp.project.modules.user.domain.interactor;

import com.todoapp.project.infrastructure.persistence.user.mappers.UserGetMapper;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.cases.GetUserCase;
import com.todoapp.project.modules.user.domain.port.UserRepository;


/**
 * Esse caso de uso é responsavel por pegar usuários
 *
 * <p>Esse interactor busca no banco de dados o usuário com o {@link UserRepository} e
 * retorna com os principais dados na classe UserGetResponse usando o {@link UserGetMapper}</p>
 *
 */
public class GetUserInteractor implements GetUserCase {

    private final UserRepository userRepository;
    private final UserGetMapper userGetMapper;

    /**
     * Cria um novo {@code GetUserInteractor}
     * @param userRepository repositorio que serve para buscar o usuário
     * @param userGetMapper mapper que converte o UserEntity para o UserGetResponse
     */
    public GetUserInteractor(UserRepository userRepository, UserGetMapper userGetMapper) {
        this.userRepository = userRepository;
        this.userGetMapper = userGetMapper;
    }


    /**
     * Executa o caso de uso de pegar usuário
     *
     * <p>Primeiro ele puxa o usuário pelo banco de dados
     * , após isso ele usa o mapper para transformar a
     * entidade na classe UserGetResponse</p>
     *
     * @param userGetRequest classe que vai conter o id do usuário
     * @return UserGetResponse classe que vai conter os atributos devolvidos do usuário
     */
    @Override
    public UserGetResponse execute(UserGetRequest userGetRequest) {
        UserEntity user = userRepository.findById(userGetRequest.id());
        return userGetMapper.toResponse(user);
    }
}
