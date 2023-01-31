package com.roboAnalises.repository;

import com.roboAnalises.domain.AnalisePadroes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnalisePadraoRepository extends JpaRepository<AnalisePadroes, Integer>, JpaSpecificationExecutor<AnalisePadroes> {

    @Query(value = "SELECT a FROM AnalisePadroes a WHERE a.isPorcentagemBoa = true ORDER BY a.estatisticas.total DESC")
    List<AnalisePadroes> findByisPorcentagemBoaIsTrue();
}
