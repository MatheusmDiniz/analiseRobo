package com.roboAnalises.testes;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.service.ApiVsStatsService;
import com.roboAnalises.service.InformacoesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class InformacoesServiceTest {

    @Mock
    InformacoesService informacoesService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        informacoesService = new InformacoesService();
    }

//    @Test
//    public void getMelhoresLigasOver25() {
//        List<AnalisePadroes> analisePadroes = new ArrayList<>();
//
//        informacoesService.getMelhoresLigasOver25(jogos);
//    }
//
//    @Test
//    public void getMelhoresLigasAmbas() {
//        List<AnalisePadroes> analisePadroes = new ArrayList<>();
//
//        informacoesService.getMelhoresLigasAmbas();
//    }

    @Test
    public void getMediaOverUltimas3Horas() {
        List<AnalisePadroes> analisePadroes = new ArrayList<>();
        List<Jogos> jogos = new ApiVsStatsService().getUltimosJogosTodasLigas();
        informacoesService.getMediaOverUltimas3Horas(LigasEnum.COPA, jogos);
    }

    @Test
    public void getMediaAmbasUltimas3Horas() {
        List<AnalisePadroes> analisePadroes = new ArrayList<>();
        List<Jogos> jogos = new ApiVsStatsService().getUltimosJogosTodasLigas();
        informacoesService.getMediaAmbasUltimas3Horas(LigasEnum.COPA, jogos);
    }

    @Test
    public void testeTodos() {
        List<Jogos> jogos = new ApiVsStatsService().getUltimosJogosTodasLigas();
        List<Jogos> ultimos200Jogos = new ApiVsStatsService(LigasEnum.COPA.getNomeOficial()).getLast200Jogos();
        System.out.println(informacoesService.getMediaOverUltimas3Horas(LigasEnum.COPA, jogos));
        System.out.println(informacoesService.getMediaAmbasUltimas3Horas(LigasEnum.COPA, jogos));

        System.out.println(informacoesService.getMediaUltimasLinhasEPorQuadrante(LigasEnum.COPA, ultimos200Jogos));

//        System.out.println(informacoesService.getMelhoresLigasOver25(jogos));
//        System.out.println(informacoesService.getMelhoresLigasAmbas(jogos));
    }



}