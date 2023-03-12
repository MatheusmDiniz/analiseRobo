package com.roboAnalises.testes;

import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.service.ApiVsStatsService;
import com.roboAnalises.service.RestricoesEntradasService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RestricoesEntradasServiceTest {



    @Test
    public void returnTrueIfVerificarMais3RedsSeguidosUltimaHoraAmbas() {
        List<Jogos> ultimos200Jogos = new ApiVsStatsService(LigasEnum.COPA.getNomeOficial()).getLast200Jogos();
        RestricoesEntradasService.returnTrueIfVerificarMais3RedsSeguidosUltimaHora(ultimos200Jogos, Apostas.AMBASMARCAM);
    }

    @Test
    public void returnTrueIfVerificarMais3RedsSeguidosUltimaHoraOver25() {
        List<Jogos> ultimos200Jogos = new ApiVsStatsService(LigasEnum.COPA.getNomeOficial()).getLast200Jogos();
        RestricoesEntradasService.returnTrueIfVerificarMais3RedsSeguidosUltimaHora(ultimos200Jogos, Apostas.OVER25);
    }
    @Test
    public void returnTrueIfVerificarHoraComMaisOverOuAmbasQueAMediaAmbas() {
        List<Jogos> ultimos200Jogos = new ApiVsStatsService(LigasEnum.COPA.getNomeOficial()).getLast200Jogos();
        RestricoesEntradasService.returnTrueIfVerificarHoraComMaisOverOuAmbasQueAMedia(ultimos200Jogos, Apostas.AMBASMARCAM, Double.valueOf(5), Double.valueOf(4));
    }

    @Test
    public void returnTrueIfVerificarHoraComMaisOverOuAmbasQueAMediaOver() {
        List<Jogos> ultimos200Jogos = new ApiVsStatsService(LigasEnum.COPA.getNomeOficial()).getLast200Jogos();
        RestricoesEntradasService.returnTrueIfVerificarHoraComMaisOverOuAmbasQueAMedia(ultimos200Jogos, Apostas.OVER25, Double.valueOf(12), Double.valueOf(4));
    }

    @Test
    public void returnTrueIfAMediaDosMinutosDaEntradaForRuim() {
        List<Jogos> ultimos200Jogos = new ApiVsStatsService().getUltimosJogosTodasLigas();
        RestricoesEntradasService.returnTrueIfAMediaDosMinutosDaEntradaForRuim(ultimos200Jogos, LigasEnum.COPA.getNome(), Apostas.AMBASMARCAM, "28");
    }


}