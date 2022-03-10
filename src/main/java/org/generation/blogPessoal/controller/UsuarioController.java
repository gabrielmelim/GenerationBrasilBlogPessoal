package org.generation.blogPessoal.controller;


import org.generation.BlogPessoal.dtos.UserCredentialDTO;
import org.generation.BlogPessoal.dtos.UserLoginDTO;
import org.generation.BlogPessoal.dtos.UserRegisterDTO;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.repository.UsuarioRepository;
import org.generation.BlogPessoal.service.UsuarioService;
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

    @GetMapping
    public List<Usuario> findAll(){
        return repository.findAll();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable (value = "id") Long id){
        return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente!");
        });
    }

    @PutMapping("/config")
    public ResponseEntity<UserCredentialDTO> getCredential(@Valid @RequestBody UserLoginDTO usuario){
        return services.validCredential(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> save (@Valid @RequestBody UserRegisterDTO usuario) {
        return services.CadastrarUsuario(usuario);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        return repository.findById(usuario.getId()).map(resp -> ResponseEntity.status(200).body
                (repository.save(usuario))).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");
        });
    }

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
