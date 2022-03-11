package org.generation.BlogPessoal.controllers;

import org.generation.BlogPessoal.dtos.UserRegisterDTO;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.repository.UsuarioRepository;
import org.generation.BlogPessoal.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

    private @Autowired TestRestTemplate testRestTemplate;
    private @Autowired UsuarioService services;
    private @Autowired UsuarioRepository repository;

    @BeforeAll
    void start() {
        repository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Teste Post Usuario")
    void createUserReturn201() {

        //GIVEN
        HttpEntity<UserRegisterDTO> request = new HttpEntity<UserRegisterDTO>(
                new UserRegisterDTO("Gabriel Boaz", "472.718.528-83", "gabriel@email.com", "1346522"));

        //WHEN
        ResponseEntity<Usuario> responses = testRestTemplate
                .exchange("/api/usuarios", HttpMethod.POST, request, Usuario.class);

        //THEN
        assertEquals(HttpStatus.CREATED, responses.getStatusCode());

    }

}
