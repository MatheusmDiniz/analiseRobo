package com.roboAnalises.repository;

import com.roboAnalises.domain.VerificarEntradas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificarEntradasRepository extends JpaRepository<VerificarEntradas, Integer> {
    VerificarEntradas findByLigaAndHoraAndMinutosAndAposta(String liga, String hora, String minutos, String aposta);

}
