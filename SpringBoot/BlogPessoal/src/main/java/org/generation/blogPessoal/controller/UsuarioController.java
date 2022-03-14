package org.generation.blogPessoal.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.generation.blogPessoal.dtos.UserCredentialDTO;
import org.generation.blogPessoal.dtos.UserLoginDTO;
import org.generation.blogPessoal.dtos.UserRegisterDTO;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {


    private @Autowired UsuarioService services;
    private @Autowired UsuarioRepository repository;


    @Operation(summary = "Buscar todos Usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários Encontrados", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @GetMapping
    public List<Usuario> findAll(){
        return repository.findAll();
    }




    @Operation(summary = "Encontrar Usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @GetMapping ("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable (value = "id") Long id){
        return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente!");
        });
    }


    @Operation(summary = "Credenciais Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário Autenticado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserCredentialDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserCredentialDTO.class)) })
    })
    @PutMapping("/auth")
    public ResponseEntity<UserCredentialDTO> getCredential(@Valid @RequestBody UserLoginDTO usuario){
        return services.validCredential(usuario);
    }


    @Operation(summary = "Cadastrar Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario Criado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterDTO.class)) }),
            @ApiResponse(responseCode = "422", description = "Usuário já Cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterDTO.class)) })
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> save (@Valid @RequestBody UserRegisterDTO usuario) {
        return services.registerUser(usuario);
    }


    @Operation(summary = "Alterar Dados Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário Alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        return repository.findById(usuario.getIdUsuario()).map(resp -> ResponseEntity.status(200).body
                (repository.save(usuario))).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");
        });
    }


    @Operation(summary = "Deletar Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário Excluído", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable (value = "id") Long id){
        return repository.findById(id).map(resp -> {
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente!");
        });
    }

}
