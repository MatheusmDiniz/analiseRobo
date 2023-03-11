package com.roboAnalises.testes;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.Entradas;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.repository.EntradasRepository;
import com.roboAnalises.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class EntradaServiceTest {

    @Mock
    EntradasService entradasService;

    @Mock
    EntradasRepository entradasRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        entradasService = new EntradasService(entradasRepository);
    }

    @Test
    public void logicaParaFazerUpdateDaEntrada() {
        ApiVsStatsService pd = new ApiVsStatsService();
        List<Jogos> ultimosJogos = pd.getUltimosJogosTodasLigas();
        entradasService.logicaParaFazerUpdateDaEntrada(entradasMock(), ultimosJogos);
    }

    private List<Entradas> entradasMock() {
        List<Entradas> entradas = new ArrayList<>();
        Entradas e = new Entradas();
        e.setFlagFinalizado(false);
        e.setFlagGrem(false);
        e.setData("2023-03-10");
        e.setMinutos("46-49-52");
        e.setAposta("AMBAS MARCAM");
        e.setHora("23");
        e.setLiga("SULAMERICA");

        entradas.add(e);
        return entradas;
    }


}