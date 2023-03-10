package com.roboAnalises.service.teste;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.Estatisticas;
import com.roboAnalises.domain.Score;
import com.roboAnalises.domain.VerificarEntradas;
import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.service.AnalisePadroesService;
import com.roboAnalises.service.ApiVsStatsService;
import com.roboAnalises.service.VerificarEntradasService;
import com.roboAnalises.service.VerificarPadroesService;
import com.roboAnalises.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestePadrao {

    @Autowired
    VerificarEntradasService verificarEntradasService;

    @Autowired
    VerificarPadroesService verificarPadroesService;


    @Autowired
    AnalisePadroesService analisePadroesService;

    public void testePadrao(){

        String padrao = "Intervalo";
        analisePadroesService.limparBanco();
        String aposta = Apostas.OVER25;
        List<String> tipoEntradaList = Arrays.asList(TipoEntrada.CIMA_MAIS_UM, TipoEntrada.QUINA, TipoEntrada.QUINTO_JOGO, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR);

        List<Jogos> jogos  = new ApiVsStatsService().getUltimosJogosTodasLigas();

        tipoEntradaList.forEach(tipoEntrada -> {
            for(int homeScoreHalf = 0; homeScoreHalf<=3;homeScoreHalf++){
                for(int awayScoreHalf = 0; awayScoreHalf<=3;awayScoreHalf++){
                    try {
                        verificarEntradasService.limparBanco();
                        verificarPadrao(jogos, homeScoreHalf, awayScoreHalf,homeScoreHalf+"x"+awayScoreHalf,
                                aposta, tipoEntrada, false);
                    }catch (Exception e){
                        continue;
                    }
                }
            }
        });
    }


    public void verificarPadrao(List<Jogos> jogos, int homeScore, int awayScore, String padrao, String aposta, String tipoEntrada, Boolean isFullTime) {
        for (Jogos jogo : jogos) {
            try {
                Score score = isFullTime ? jogo.getScoreFullTime() : jogo.getScoreHalfTime();
                if (score.getHome() == homeScore && score.getAway() == awayScore) {
                    verificarPadroesService.salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), aposta, tipoEntrada, padrao);
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas(), tipoEntrada);

    }

    public void verificarApostaIntervaloEFullTime(List<Jogos> jogos, int homeScoreHalf, int awayScoreHalf, int homeScoreFull, int awayScoreFull, String padrao, String aposta, String tipoEntrada) {
        for (Jogos jogo : jogos) {
            try {
                if (jogo.getScoreHalfTime().getHome() == homeScoreHalf &&
                        jogo.getScoreHalfTime().getAway() == awayScoreHalf &&
                        jogo.getScoreFullTime().getHome() == homeScoreFull &&
                        jogo.getScoreFullTime().getAway() == awayScoreFull) {
                    verificarPadroesService.salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), aposta, tipoEntrada, padrao);
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas(), tipoEntrada);
    }

    public void verificarEntradas(List<VerificarEntradas> entradas, String tipoEntrada) {
        List<VerificarEntradas> entradasEuro = new ArrayList<>();
        List<VerificarEntradas> entradasCopa = new ArrayList<>();
        List<VerificarEntradas> entradasSulAmerica = new ArrayList<>();
        List<VerificarEntradas> entradasPremier = new ArrayList<>();

        if(entradas.isEmpty()){
            return;
        }

        entradas.forEach(e ->{
            if(e.getLiga().equals(LigasEnum.EURO.getNome())){
                entradasEuro.add(e);
            }

            if(e.getLiga().equals(LigasEnum.COPA.getNome())){
                entradasCopa.add(e);
            }

            if(e.getLiga().equals(LigasEnum.PREMIER.getNome())){
                entradasPremier.add(e);
            }

            if(e.getLiga().equals(LigasEnum.SULAMERICA.getNome())){
                entradasSulAmerica.add(e);
            }
        });

        Estatisticas estatisticas = new Estatisticas();

        estatisticas = verificarPadroesService.createEstatisticas(entradasEuro, LigasEnum.EURO.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.EURO.getNome(),estatisticas, tipoEntrada);

        estatisticas = verificarPadroesService.createEstatisticas(entradasCopa, LigasEnum.COPA.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.COPA.getNome(),estatisticas, tipoEntrada);

        estatisticas = verificarPadroesService.createEstatisticas(entradasPremier, LigasEnum.PREMIER.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.PREMIER.getNome(),estatisticas, tipoEntrada);

        estatisticas = verificarPadroesService.createEstatisticas(entradasSulAmerica, LigasEnum.SULAMERICA.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.SULAMERICA.getNome(),estatisticas, tipoEntrada);

    }

    private void salvarAnalisePadroes(String padrao, String liga, Estatisticas estatisticas, String tipoEntrada) {
        AnalisePadroes analisePadroes = new AnalisePadroes();
        analisePadroes.setPadrao(padrao);
        analisePadroes.setLiga(liga);
        analisePadroes.setIsPorcentagemBoa(Util.verificarPorcentagemPadrao(estatisticas.getTotal(), estatisticas.getContReds(), estatisticas.getContEntradas(), estatisticas.getContGrens()));
        analisePadroes.setEstatisticas(Util.estatisticaCompleto(estatisticas));
        analisePadroes.setTipoEntrada(tipoEntrada);
        try{
            if(analisePadroes.getIsPorcentagemBoa()){
                analisePadroesService.salvarEntrada(analisePadroes);
            }
        }catch (Exception e){
            return;
        }
    }

    public void logicaParaverificarPlacarIntervaloeJOgoTodo(){
//        for(int homeScoreHalf = 0; homeScoreHalf<=3;homeScoreHalf++){
//            for(int awayScoreHalf = 0; awayScoreHalf<=3;awayScoreHalf++){
//                for(int homeScoreFull = 0; homeScoreFull<=3;homeScoreFull++){
//                    for(int awayScoreFull = 0; awayScoreFull<=3;awayScoreFull++){
//                        try {
//                              verificarEntradasService.limparBanco();
//                            verificarApostaIntervaloEFullTime(jogos,
//                                    homeScoreHalf, awayScoreHalf, homeScoreFull, awayScoreFull,
//                                    homeScoreHalf+"x"+awayScoreHalf+"/"+homeScoreFull+"x"+awayScoreFull,
//                                    aposta, tipoEntrada);
//                        }catch (Exception e){
//                            continue;
//                        }
//                    }
//                }
//            }
//        }
    }

    public void logiaParaVeririficarPlacarJogoTodo(){
//       for(int homeScoreFull = 0; homeScoreFull<=3;homeScoreFull++){
//                for(int awayScoreFull = 0; awayScoreFull<=3;awayScoreFull++){
//                    try {
//                        verificarPadrao(jogos, homeScoreFull, awayScoreFull,homeScoreFull+"x"+awayScoreFull,
//                                aposta, tipoEntrada, true);
//                    }catch (Exception e){
//                        continue;
//                    }
//                }
//            }
//            verificarEntradas(verificarEntradasService.findAllEntradas(), tipoEntrada);
//            verificarEntradasService.limparBanco();
    }

    public void logicaParaVeririficarPlacarIntervalo(){
//        for(int homeScoreHalf = 0; homeScoreHalf<=3;homeScoreHalf++){
//            for(int awayScoreHalf = 0; awayScoreHalf<=3;awayScoreHalf++){
//                try {
//                    verificarEntradasService.limparBanco();
//                    verificarPadrao(jogos, homeScoreHalf, awayScoreHalf,homeScoreHalf+"x"+awayScoreHalf,
//                            aposta, tipoEntrada, false);
//                }catch (Exception e){
//                    continue;
//                }
//            }
//        }
    }
}
