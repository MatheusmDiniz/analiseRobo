package com.roboAnalises.testes;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.repository.AnalisePadraoRepository;
import com.roboAnalises.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class RoboServiceTest {

    @Mock
    RoboService roboService;

    @Mock
    EntradasService entradasService;

    @Mock
    AnalisePadroesService analisePadroesService;

    @Mock
    TelegramService telegramService;


    @MockBean
    private ApiVsStatsService apiVsStatsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roboService = new RoboService(entradasService, analisePadroesService, telegramService);
        apiVsStatsService = new ApiVsStatsService();
    }

    @Test
    public void testVerificarPadrao() {
        List<AnalisePadroes> analisePadroes = getAnaLisePadroes();

        roboService.verificarPadrao(analisePadroes);
    }

    private List<AnalisePadroes> getAnaLisePadroes() {
        List<AnalisePadroes> analisePadroesList = new ArrayList<>();
        AnalisePadroes a = new AnalisePadroes();
        a.setPadrao("ambas3x0JogoTodoQuinto");
        a.setLiga("PREMIER");
        a.setIsPorcentagemBoa(true);

        analisePadroesList.add(a);
        return analisePadroesList;
    }

    private List<Jogos> getUltimosJogos() {
       List<Jogos> jogos = new ArrayList<>();

       Jogos j = new Jogos();
       j.setLiga(LigasEnum.COPA.getNome());
       j.setId(20230304);

       jogos.add(j);
       return jogos;
    }
}