package com.todoapp.project.modules.user.aplication.controller;

import com.todoapp.project.core.abstraction.UserAuthenticationService;
import com.todoapp.project.infrastructure.api.dto.ApiResponseDto;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateRequest;
import com.todoapp.project.modules.user.aplication.dto.create.UserCreateResponse;
import com.todoapp.project.modules.user.aplication.dto.delete.UserDeleteRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditRequest;
import com.todoapp.project.modules.user.aplication.dto.edit.UserEditResponse;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetRequest;
import com.todoapp.project.modules.user.aplication.dto.get.UserGetResponse;
import com.todoapp.project.modules.user.aplication.port.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Classe responsável por gerenciar as requisições de rotas
 *
 *
 * <p>Essa classe é responsável por receber a requisições e delegar
 * a execuções desse códigos para a interface {@link UserService}.
 * Ela é responsável por devolver a requisição caso ocorra tudo
 * corretamente</p>
 *
 */
@RestController
@RequestMapping("/apitodo/user")
public class UserController {

    private final UserService userService;
    private final UserAuthenticationService userAuthenticationService;

    /**
     * inicializa a classe UserController
     *
     * @param userService interface responsável por realizar ações com o usuários
     * @param userAuthenticationService interface responsável por buscar informações do usuário autenticado
     *
     */
    public UserController(UserService userService, UserAuthenticationService userAuthenticationService) {
        this.userService = userService;
        this.userAuthenticationService = userAuthenticationService;
        ;
    }

    /**
     * Método responsável por criar um usuário
     *
     * <p>Esse método primeiro pega o id do usuário que está executando
     * a requisição, depois delega a ação para o {@link UserService}.
     * Caso de tudo certo ele devolve a requisição</p>
     *
     * @param userCreateRequest Classe que contém informações do usuário que irá ser criado
     * @return ResponseEntity<ApiResponseDto<UserCreateResponse>> resposta da api que contém o usuário criado
     *
     */
    @PostMapping
    public ResponseEntity<ApiResponseDto<UserCreateResponse>> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        UUID id = userAuthenticationService.getIdUserAuthentication();

        UserCreateResponse userCreateResponse = userService.createUser(userCreateRequest, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.CREATED.value(), "User created with success", userCreateResponse));
    }

    /**
     * Método responsável por editar um usuário
     *
     * <p>Esse método primeiro pega o id do usuário que está executando
     * a requisição, depois delega a ação para o {@link UserService}.
     * Caso de tudo certo ele devolve a requisição</p>
     *
     * @param userEditRequest Classe que contém as informações que serão alteradas
     * @param id id do usuário que será alterado
     * @return ResponseEntity<ApiResponseDto<UserEditResponse>> resposta da api que contém o usuário editado
     *
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserEditResponse>>  editUser(@RequestBody @Valid UserEditRequest userEditRequest, @PathVariable UUID id){
        UUID idExecuter = userAuthenticationService.getIdUserAuthentication();

        UserEditResponse userEditResponse = userService.editResponse(userEditRequest, idExecuter, id);

        return ResponseEntity.ok(ApiResponseDto.success(200, "User edited with sucess", userEditResponse));
    }

    /**
     * Método responsável por deletar um usuário
     *
     * <p>Esse método primeiro pega o id do usuário que está executando
     * a requisição, depois delega a ação para o {@link UserService}.
     * Caso de tudo certo ele devolve a requisição</p>
     *
     * @param id id do usuário que será deletado
     * @return ResponseEntity<ApiResponseDto<Void>> resposta da api que contém a mensagem de sucesso
     *
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable UUID id){
        UUID idExecuter = userAuthenticationService.getIdUserAuthentication();

        UserDeleteRequest userDeleteRequest = new UserDeleteRequest(id);
        userService.deleteUser(userDeleteRequest, id);

        return ResponseEntity.ok(ApiResponseDto.success(200, "User deleted with sucess"));
    }

    /**
     * Método responsável por buscar um usuário pelo ID.
     *
     * <p>Cria um objeto {@link UserGetRequest} com o ID fornecido
     * e delega a busca ao {@link UserService}.Retorna uma resposta da API
     * contendo os dados do usuário encontrado, se a busca for bem-sucedida.</p>
     *
     * @param id O ID do usuário a ser buscado.
     * @return ResponseEntity<ApiResponseDto<UserGetResponse>> A resposta da API com os dados do usuário.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserGetResponse>> getUser(@PathVariable UUID id){

        UserGetRequest userGetRequest = new UserGetRequest(id);
        UserGetResponse userGetResponse = userService.getUser(userGetRequest);

        return ResponseEntity.ok(ApiResponseDto.success(200, "User found with sucess", userGetResponse));
    }


}
