package com.roboAnalises.testes;

import com.roboAnalises.domain.AnalisePadroes;
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
        entradasService.logicaParaFazerUpdateDaEntrada(null, ultimosJogos);
    }


}