package com.roboAnalises.repository;

import com.roboAnalises.domain.Classificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Classificacao, Integer> {
    List<Classificacao> findAllByLiga(String nome);
}

