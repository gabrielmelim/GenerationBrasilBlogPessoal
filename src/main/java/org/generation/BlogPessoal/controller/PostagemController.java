package org.generation.BlogPessoal.controller;

import java.util.List;

import org.generation.BlogPessoal.model.Postagem;
import org.generation.BlogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postagens")
@CrossOrigin("*")
public class PostagemController {

    @Autowired
    private PostagemRepository repositoty;

    @GetMapping
    public ResponseEntity<List<Postagem>> GetAll() {
        return ResponseEntity.ok(repositoty.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> GetById(@PathVariable long id)
    {
        return repositoty.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo)
    {
        return ResponseEntity.ok(repositoty.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Postagem> post(@RequestBody Postagem postagem)
    {
        return  ResponseEntity.status(HttpStatus.CREATED).body(repositoty.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put(@RequestBody Postagem postagem)
    {
        return  ResponseEntity.status(HttpStatus.OK).body(repositoty.save(postagem));
    }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable long id)
   {
       repositoty.deleteById(id);
   }



}
