package com.roboAnalises.service;

import com.roboAnalises.domain.VerificarEntradas;
import com.roboAnalises.repository.VerificarEntradasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VerificarEntradasService {

    @Autowired
    VerificarEntradasRepository entradasRepository;

    public boolean verificarEntradaDuplicada(VerificarEntradas entrada) {
        VerificarEntradas e = entradasRepository.findByLigaAndHoraAndMinutosAndAposta(entrada.getLiga(), entrada.getHora(), entrada.getMinutos(), entrada.getAposta());

        if(Objects.nonNull(e)){
            return false;
        }

        return true;
    }

    public void salvarEntrada(VerificarEntradas entrada){
        entradasRepository.save(entrada);
    }

    public List<VerificarEntradas> findAllEntradas() {
        return entradasRepository.findAll();
    }


    public void limparBanco() {
        entradasRepository.deleteAll();
    }



}
