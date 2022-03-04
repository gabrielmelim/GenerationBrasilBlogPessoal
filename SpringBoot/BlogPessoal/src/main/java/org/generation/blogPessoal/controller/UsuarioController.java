package org.generation.BlogPessoal.controller;

import org.generation.BlogPessoal.model.UserLogin;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/logar")
    public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user)
    {
        return usuarioService.Logar(user).map(resp ->  ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> post (@RequestBody Usuario usuario)
    {
        return  ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioService.CadastrarUsuario(usuario));
    }

}
