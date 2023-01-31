package com.roboAnalises.service;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.repository.AnalisePadraoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalisePadroesService {

    AnalisePadraoRepository analisePadraoRepository;

    @Autowired
    public AnalisePadroesService(AnalisePadraoRepository analisePadraoRepository){
        this.analisePadraoRepository = analisePadraoRepository;
    }

    public void salvarEntrada(AnalisePadroes analisePadroes){
        analisePadraoRepository.save(analisePadroes);
    }

    public void limparBanco() {
        analisePadraoRepository.deleteAll();
    }

    public List<AnalisePadroes> getAnalisePadroes() {
        return analisePadraoRepository.findByisPorcentagemBoaIsTrue();
    }
}
