package org.generation.blogPessoal.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de Postagem com endPoints (Get, Post, Put e Delete)
 * Puxar dados da Postagem, Cadastrar Postagem, Alterar dados da Postagem e
 * deletar Postagem
 *
 * @author Gabriel
 *
 **/

@RestController
@RequestMapping("/api/postagens")
@CrossOrigin("*")
public class PostagemController {

    @Autowired
    private PostagemRepository repository;


    @Operation(summary = "Buscar todas Postagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagens encontradas", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) })
    })
    @GetMapping
    public ResponseEntity<List<Postagem>> GetAll() {
        return ResponseEntity.ok(repository.findAll());
    }


    @Operation(summary = "Buscar Postagem pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagem encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) }),
            @ApiResponse(responseCode = "404", description = "Postagem não encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> GetById(@PathVariable long id)
    {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar Postagem pelo Titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Titulo encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) }),
            @ApiResponse(responseCode = "404", description = "Titulo não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) })
    })
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo)
    {
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }


    @Operation(summary = "Cadastrar Postagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Postagem cadastrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "400", description = "Postagem não cadastrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
    })
    @PostMapping
    public ResponseEntity<Postagem> post(@RequestBody Postagem postagem)
    {
        return  ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    }


    @Operation(summary = "Alterar Postagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Postagem alterada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "400", description = "Postagem não alterada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
    })
    @PutMapping
    public ResponseEntity<Postagem> put(@RequestBody Postagem postagem)
    {
        return  ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    }

    @Operation(summary = "Deletar Postagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Postagem Excluída", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) }),
            @ApiResponse(responseCode = "404", description = "Postagem não excluida", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Postagem.class)) })
    })
   @DeleteMapping("/{id}")
   public void delete(@PathVariable long id)
   {
       repository.deleteById(id);
   }



}
