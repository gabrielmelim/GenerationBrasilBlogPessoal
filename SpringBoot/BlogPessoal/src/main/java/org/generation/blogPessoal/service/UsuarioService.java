package org.generation.blogPessoal.service;


import org.generation.blogPessoal.dtos.UserCredentialDTO;
import org.generation.blogPessoal.dtos.UserLoginDTO;
import org.generation.blogPessoal.dtos.UserRegisterDTO;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
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

/**
 * Class service of operations from Usuario
 *
 * @author Melim
 * @since 13/03/2022
 * @version 1.0
 */
@Service
public class UsuarioService {

    @Autowired private UsuarioRepository repository;
    private Usuario novoUsuario;

    /**
     * Meétodo de registrar um novo usuario model -> Usuario
     *
     * @param user UserRegisterDTO
     * @return ResponseEntity<Usuario>
     */
    public ResponseEntity<Usuario> registerUser(UserRegisterDTO user) {
        Optional<Usuario> optional = repository.findByEmail(user.getEmail());

        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setSenha(encoder.encode(user.getSenha()));


            novoUsuario = new Usuario(
                    user.getNome(),
                    user.getCpf(),
                    user.getEmail(),
                    user.getSenha());

            return ResponseEntity.status(201).body(repository.save(novoUsuario));
        }
    }

    /**
     * Método de login do usuario model -> Usuario
     *
     * @param usuario VoterLoginDTO
     * @return ResponseEntity<Usuario>
     */
    public ResponseEntity<UserCredentialDTO> validCredential(@Valid UserLoginDTO usuario){
        return repository.findByEmail(usuario.getEmail()).map(u -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(usuario.getSenha(), u.getSenha())){
                UserCredentialDTO credential = new UserCredentialDTO(
                        u.getIdUsuario(),
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

    /**
     * Método de gerar um basic token
     *
     * @param email  String email
     * @param senha  String password
     * @return String
     */
    private String generatorToken(String email, String senha){
        String structure = email + ":" + senha;
        byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(structureBase64);
    }
}
