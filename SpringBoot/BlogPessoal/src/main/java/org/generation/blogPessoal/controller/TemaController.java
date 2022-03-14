package org.generation.blogPessoal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tema")
public class TemaController {

    @Autowired
    private TemaRepository repository;

    @Operation(summary = "Buscar todos os Temas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temas encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) })
    })
    @GetMapping
    public ResponseEntity<List<Tema>> getAll()
    {
        return ResponseEntity.ok(repository.findAll());
    }


    @Operation(summary = "Buscar Tema pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tema encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "404", description = "Tema não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) })
    })
    @GetMapping("/{id}") public ResponseEntity<Tema> getById (@PathVariable long id)
    {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Buscar produtos por Nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temas Encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "404", description = "Temas não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) })
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Tema>> getByName (@PathVariable String nome)
    {
        return ResponseEntity.ok(repository.findAllByTemaContainingIgnoreCase(nome));
    }


    @Operation(summary = "Cadastrar Tema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tema cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "400", description = "Tema não cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) })
    })
    @PostMapping
    public ResponseEntity<Tema> post (@RequestBody Tema tema)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }


    @Operation(summary = "Alterar Tema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tema alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "400", description = "Tema não alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) })
    })
    @PutMapping
    public ResponseEntity<Tema> put (@RequestBody Tema tema)
    {
        return ResponseEntity.ok(repository.save(tema));
    }

    @Operation(summary = "Deletar Tema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tema Excluído", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) }),
            @ApiResponse(responseCode = "404", description = "Tema não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tema.class)) })
    })
    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id)
    {
        repository.deleteById(id);
    }





}
