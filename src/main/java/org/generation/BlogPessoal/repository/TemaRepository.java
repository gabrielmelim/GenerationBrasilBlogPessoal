package org.generation.BlogPessoal.repository;

import org.generation.BlogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);

}
