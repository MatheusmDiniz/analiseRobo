package com.roboAnalises.service;

import com.roboAnalises.domain.Entradas;
import com.roboAnalises.repository.EntradasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EntradasService {

    EntradasRepository entradasRepository;

    @Autowired
    public EntradasService(EntradasRepository entradasRepository){
        this.entradasRepository = entradasRepository;
    }

    public void salvarEntrada(Entradas entrada){
        entradasRepository.save(entrada);
    }

    public boolean verificarEntradaDuplicada(Entradas entrada) {
        Entradas e = entradasRepository.findByLigaAndHoraAndMinutosAndAposta(entrada.getLiga(), entrada.getHora(), entrada.getMinutos(), entrada.getAposta());

        if(Objects.nonNull(e)){
            return false;
        }

        return true;
    }

    public List<Entradas> pegarEntradasNaoFinalizadas(){
        return entradasRepository.findEntradasAndNotFinalizadas();
    }

    public void limparBanco() {
        entradasRepository.deleteAll();
    }

    public Long findEntradasNaoFinalizadas() {
        return entradasRepository.findEntradasNaoFinalizadas();
    }
}
