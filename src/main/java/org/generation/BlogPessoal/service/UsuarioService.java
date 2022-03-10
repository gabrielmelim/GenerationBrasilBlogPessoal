package org.generation.BlogPessoal.service;


import org.generation.BlogPessoal.dtos.UserCredentialDTO;
import org.generation.BlogPessoal.dtos.UserLoginDTO;
import org.generation.BlogPessoal.dtos.UserRegisterDTO;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    private Usuario novoUsuario;

    public ResponseEntity<Usuario> CadastrarUsuario(UserRegisterDTO usuario) {
        Optional<Usuario> optional = repository.findByEmail(usuario.getEmail());
        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já em uso");
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setSenha(encoder.encode(usuario.getSenha()));

            novoUsuario = new Usuario(
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getEmail(),
                    usuario.getSenha());

            return ResponseEntity.status(201).body(repository.save(novoUsuario));
        }
    }

    public ResponseEntity<UserCredentialDTO> validCredential(@Valid UserLoginDTO usuario){
        return repository.findByEmail(usuario.getEmail()).map(u -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(usuario.getSenha(), u.getSenha())){
                UserCredentialDTO credential = new UserCredentialDTO(
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        generatorToken(usuario.getEmail(), usuario.getSenha()));
                return ResponseEntity.status(200).body(credential);
            }
            else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
            }
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "E-mail não encontrado"));
    }

    private String generatorToken(String email, String senha){
        String structure = email + ":" + senha;
        byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(structureBase64);
    }
}
