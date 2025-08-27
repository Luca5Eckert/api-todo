package com.todoapp.project.infrastructure.persistence.user.mappers;

import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.domain.UserEntity;
import com.todoapp.project.modules.user.domain.valueobjects.Email;
import com.todoapp.project.modules.user.domain.valueobjects.Name;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por mapear dados entre objetos de requisição,
 * entidades de domínio e objetos de resposta para a edição de um usuário.
 *
 * <p>Esta classe segue o padrão de Mapeador (Mapper) para garantir a
 * separação de responsabilidades e evitar o acoplamento direto entre as camadas
 * da aplicação.</p>
 */
@Component
public class UserEditMapper {

    /**
     * Atualiza a entidade de usuário com os dados de uma requisição de edição.
     *
     * <p>Este método modifica a entidade {@code user} em vigor, definindo
     * seus atributos de nome e e-mail com base nos valores fornecidos na requisição.</p>
     *
     * @param userEditRequest O DTO {@link UserEditRequest} contendo os novos dados do usuário (nome, e-mail).
     * @param user A entidade de usuário {@link UserEntity} a ser atualizada.
     */
    public void toEntity(UserEditRequest userEditRequest, UserEntity user){
        user.setEmail(new Email(userEditRequest.email()));
        user.setName(new Name(userEditRequest.name()));
    }

    /**
     * Mapeia uma entidade de usuário para um DTO de resposta de edição.
     *
     * <p>Este método cria e retorna um novo objeto {@link UserEditResponse}
     * contendo informações essenciais da entidade de usuário.</p>
     *
     * @param user A entidade de usuário {@link UserEntity} a ser mapeada.
     * @return Um DTO {@link UserEditResponse} contendo o ID, nome e e-mail do usuário.
     */
    public UserEditResponse toResponse(UserEntity user){
        return new UserEditResponse(user.getId(), user.getName().getValue(), user.getEmail().getValue());
    }

}