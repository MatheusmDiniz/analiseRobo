package com.roboAnalises.service;

import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.enums.Padrao;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.repository.VerificarEntradasRepository;
import com.roboAnalises.util.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestarNovosPadroesTest {

//    @Mock
//    VerificarEntradasService verificarEntradasService;
//
//    @Mock
//    AnalisePadroesService analisePadroesService;
//
//    @Mock
//    VerificarEntradasRepository entradasRepository;

    VerificarEntradasRepository entradasRepository;

    @InjectMocks
    VerificarEntradasService verificarEntradasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
//        entrada = new VerificarEntradas("Liga 1", 10, 20, "Aposta 1");
    }

    @Test
    void getLastGamesService(){
        String padrao = "";
        String aposta = Apostas.AMBASMARCAM;
        String tipoEntrada = TipoEntrada.QUINTO_JOGO;

        int homeScoreHalf = 1;
        int awayScoreHalf = 1;
        int homeScoreFull = 1;
        int awayScoreFull = 1;

//        new VerificarEntradasRepository().deleteAll();

        List<Jogos> jogos  = new ApiVsStatsService().getUltimosJogosTodasLigas();

//        VerificarPadroesService verificarPadroesService = new VerificarPadroesService(verificarEntradasService, analisePadroesService);
//        verificarPadroesService.verificarPadrao(jogos, 2, 0, Padrao.OVER25_2x0_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);
//        verificarApostaIntervaloEFullTime(jogos, 0, 0, 0, 1, Padrao.OVER25_0x0INTERVALO_0x1JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);

    }
}
