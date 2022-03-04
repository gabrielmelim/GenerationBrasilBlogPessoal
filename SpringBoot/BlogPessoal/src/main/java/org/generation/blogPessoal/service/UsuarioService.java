package org.generation.BlogPessoal.service;


import org.generation.BlogPessoal.model.UserLogin;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario CadastrarUsuario (Usuario usuario)
    {
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        String senhaEnconder = enconder.encode(usuario.getSenha());
        usuario.setSenha(senhaEnconder);

        return repository.save(usuario);
    }

    public Optional<UserLogin> Logar(Optional<UserLogin> user)
    {
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

        if (usuario.isPresent())
        {
            if (enconder.matches(user.get().getSenha(),usuario.get().getSenha()))
            {
                String auth = user.get().getUsuario() + ":" + user.get().getSenha();
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

                String authHeader = "Basic" + new String(encodedAuth);
                user.get().setToken(authHeader);
                user.get().setNome(usuario.get().getNome());

                return user;
            }
        }
        return null;
    }

}
