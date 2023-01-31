package com.roboAnalises.repository;

import com.roboAnalises.domain.Entradas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradasRepository extends JpaRepository<Entradas, Integer>, JpaSpecificationExecutor<Entradas> {
    Entradas findByLigaAndHoraAndMinutosAndAposta(String liga, String hora, String minutos, String aposta);

    @Query(value = "SELECT e FROM Entradas e WHERE e.flagFinalizado = false OR e.flagGrem = false")
    List<Entradas> findEntradasAndNotFinalizadas();

    @Query(value = "SELECT e FROM Entradas e WHERE e.flagFinalizado = false AND e.liga = :liga AND e.aposta = :aposta")
    Entradas findByLigaAndApostaAndFlagFinalizado(@Param("liga") String liga, @Param("aposta") String aposta);

    @Query(value = "SELECT count(e) FROM Entradas e WHERE e.flagFinalizado = false")
    Long findEntradasNaoFinalizadas();
}
