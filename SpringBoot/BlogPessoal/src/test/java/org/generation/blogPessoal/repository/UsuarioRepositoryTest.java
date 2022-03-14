package org.generation.blogPessoal.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    private @Autowired UsuarioRepository repository;


    //GIVEN que eu tenho email@email.com no banco
    //WHEN eu pesquiso por email@email.com
    //THEN ele me retorna email@email.com

    @BeforeAll
    void start()
    {

    }

    @Test
    void contextLoads() {

    }
}
