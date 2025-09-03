package com.todoapp.project.core.abstraction;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

/**
 * Interface para o serviço de autenticação de usuário
 *
 * <p>Define o contrato para pegar informarções do usuário
 * autenticado</p>
 */
public interface UserAuthenticationService {


    /**
     * Método para buscar o id do usuário autenticado
     *
     * @return long id do usuário autenticado
     */
    long getIdUserAuthentication();


    /**
     * Método para buscar o UserDetails do usuário autenticado
     *
     * @return UserDetails com as informações do usuário autenticado
     */
    UserDetails getUserDetails();

}
