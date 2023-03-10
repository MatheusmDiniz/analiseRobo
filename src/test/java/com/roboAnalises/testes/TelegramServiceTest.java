package com.roboAnalises.testes;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class TelegramServiceTest {

    @Mock
    TelegramService telegramService;

    @Mock
    EntradasService entradasService;

    @Mock
    ClassificacaoService classificacaoService;

    @Mock
    InformacoesService informacoesService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        telegramService = new TelegramService(entradasService, classificacaoService, informacoesService);
    }

    @Test
    public void montarMensagem() {
        List<AnalisePadroes> analisePadroes = new ArrayList<>();

        telegramService.montarMensagem(getJogo(), Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO);
    }

    private Jogos getJogo() {

       Jogos j = new Jogos();
       j.setLiga(LigasEnum.COPA.getNome());
       j.setId(20230304);
       j.setHour(3);
       j.setMinute(40);

       return j;
    }
}