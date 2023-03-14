package com.roboAnalises.service;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.Estatisticas;
import com.roboAnalises.domain.Score;
import com.roboAnalises.domain.VerificarEntradas;
import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.enums.Padrao;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class VerificarPadroesService {

    @Autowired
    VerificarEntradasService verificarEntradasService;

    @Autowired
    AnalisePadroesService analisePadroesService;


    public void verificarPadroes(){

        verificarEntradasService.limparBanco();
        analisePadroesService.limparBanco();

        padroes();


    }

    private void padroes() {
        List<Jogos> jogos  = new ApiVsStatsService().getUltimosJogosTodasLigas();

        verificarPadraoResultadosIguaisEntraQuinaOver25(jogos);

        verificarPadrao(jogos, 2, 0, Padrao.OVER25_2x0_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 3, 1, Padrao.AMBAS_3x1_JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 2, 2, Padrao.AMBAS_2x2_JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 1, 3, Padrao.AMBAS_1x3_JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 0, 0, Padrao.AMBAS_0x0_JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 0, 2, Padrao.AMBAS_0x2FORA_JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 2, 0, Padrao.AMBAS_2x0CASA_JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 0, 0, Padrao.AMBAS_0x0_INTERVALO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false);
        verificarPadrao(jogos, 1, 1, Padrao.AMBAS_1x1_INTERVALO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false);
        verificarPadrao(jogos, 2, 2, Padrao.OVER25_2x2_JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 3, 1, Padrao.OVER25_3x1_JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 1, 3, Padrao.OVER25_1x3_JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 0, 0, Padrao.OVER25_0x0_INTERVALO_QUINA, Apostas.OVER25, TipoEntrada.QUINA, false);
        verificarPadrao(jogos, 0, 2, Padrao.Over25_0x2_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 1, 2, Padrao.Over25_1x2_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 2, 3, Padrao.Over25_2x3_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 2, 0, Padrao.AMBAS_2x0_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 3, 0, Padrao.AMBAS_3x0_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 0, 2, Padrao.AMBAS_0x2_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 0, 1, Padrao.AMBAS_0x1_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 1, 2, Padrao.AMBAS_1x2_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 1, 3, Padrao.AMBAS_1x3_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 2, 3, Padrao.AMBAS_2x3_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 1, 0, Padrao.AMBAS_1x0_INTERVALO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false);
        verificarPadrao(jogos, 2, 0, Padrao.AMBAS_2x0_INTERVALO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, false);
        verificarPadrao(jogos, 1, 2, Padrao.AMBAS_1x2_JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 2, 2, Padrao.AMBAS_2x2_JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 2, 1, Padrao.AMBAS_2x1_JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 0, 2, Padrao.AMBAS_0x2_INTERVALO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, false);
        verificarPadrao(jogos, 1, 2, Padrao.AMBAS_1x2_JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 3, 0, Padrao.AMBAS_3x0_JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 0, 1, Padrao.AMBAS_0x1_INTERVALO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, false);
        verificarPadrao(jogos, 0, 2, Padrao.AMBAS_0x2_INTERVALO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, false);
        verificarPadrao(jogos, 3, 1, Padrao.AMBAS_3x1_JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 2, 0, Padrao.AMBAS_2x0_INTERVALO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, false);
        verificarPadrao(jogos, 0, 0, Padrao.AMBAS_0x0_JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, true);
        verificarPadrao(jogos, 0, 2, Padrao.AMBAS_0x2_JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, true);
        verificarPadrao(jogos, 3, 1, Padrao.AMBAS_3x1_JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR, true);
        verificarPadrao(jogos, 2, 1, Padrao.OVER25_2x1_JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 2, 2, Padrao.OVER25_2x2_JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA, true);
        verificarPadrao(jogos, 1, 1, Padrao.OVER25_1x1_INTERVALO_QUINA, Apostas.OVER25, TipoEntrada.QUINA, false);
        verificarPadrao(jogos, 0, 2, Padrao.OVER25_0x2_INTERVALO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, false);
        verificarPadrao(jogos, 2, 1, Padrao.OVER25_2x1_JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 0, 3, Padrao.OVER25_0x3_JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, true);
        verificarPadrao(jogos, 0, 3, Padrao.Over25_0x3_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 2, 2, Padrao.Over25_2x2_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);
        verificarPadrao(jogos, 3, 1, Padrao.Over25_3x1_JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO, true);

        verificarApostaIntervaloEFullTime(jogos, 0, 0, 0, 1, Padrao.OVER25_0x0INTERVALO_0x1JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 1, 0, Padrao.OVER25_0x0INTERVALO_1x0JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 2, 0, Padrao.OVER25_0x0INTERVALO_2x0JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 0, 1, Padrao.OVER25_0x1INTERVALO_0x1JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 0, 2, Padrao.OVER25_0x1INTERVALO_0x2JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 1, 0, 2, 0, Padrao.OVER25_1x0INTERVALO_2x0JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 1, 1, 1, 1, Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_QUINA, Apostas.OVER25, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 1, 0, Padrao.OVER25_0x0INTERVALO_1x0JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 0, 2, 0, 2, Padrao.OVER25_0x2INTERVALO_0x2JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 1, 0, 1, 1, Padrao.OVER25_1x0INTERVALO_1x1JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 1, 0, 2, 0, Padrao.OVER25_1x0INTERVALO_2x0JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 0, 1, Padrao.AMBAS_0x0INTERVALO_0x1JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 1, 2, Padrao.AMBAS_0x1INTERVALO_1x2JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 1, 1, 1, 1, Padrao.AMBAS_1x1INTERVALO_1x1JOGOTODO_QUINA, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
        verificarApostaIntervaloEFullTime(jogos, 1, 1, 1, 1, Padrao.AMBAS_1x1INTERVALO_1x1JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 0, 1, Padrao.AMBAS_0x0INTERVALO_0x1JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 0, 1, Padrao.AMBAS_0x1INTERVALO_0x1JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 0, 2, Padrao.AMBAS_0x1INTERVALO_0x2JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 1, 2, Padrao.AMBAS_0x1INTERVALO_1x2JOGOTODO_CIMA, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 1, 1, Padrao.AMBAS_0x0INTERVALO_1x1JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 1, 0, 2, 0, Padrao.AMBAS_1x0INTERVALO_2x0JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 0, 2, Padrao.AMBAS_0x1INTERVALO_0x2JOGOTODO_QUINTO, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 1, 1, Padrao.AMBAS_0x0INTERVALO_1x1JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 0, 2, Padrao.AMBAS_0x1INTERVALO_0x2JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 1, 1, Padrao.AMBAS_0x1INTERVALO_1x1JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR);
        verificarApostaIntervaloEFullTime(jogos, 2, 0, 2, 0, Padrao.AMBAS_2x0INTERVALO_2x0JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.AMBASMARCAM, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR);
        verificarApostaIntervaloEFullTime(jogos, 0, 0, 0, 1, Padrao.OVER25_0x0INTERVALO_0x1JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 1, 0, 1, 1, Padrao.OVER25_1x0INTERVALO_1x1JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 1, 1, 1, 1, Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_CIMA, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 0, 1, Padrao.OVER25_0x1INTERVALO_0x1JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 1, 1, 1, 1, Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_QUINTO, Apostas.OVER25, TipoEntrada.QUINTO_JOGO);
        verificarApostaIntervaloEFullTime(jogos, 0, 1, 1, 1, Padrao.OVER25_0x1INTERVALO_1x1JOGOTODO_QUINTO_HORA_ANTERIOR, Apostas.OVER25, TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR);

    }

    public void verificarPadrao(List<Jogos> jogos, int homeScore, int awayScore, String padrao, String aposta, String tipoEntrada, Boolean isFullTime) {
        for (Jogos jogo : jogos) {
            try {
                Score score = isFullTime ? jogo.getScoreFullTime() : jogo.getScoreHalfTime();
                if (score.getHome() == homeScore && score.getAway() == awayScore) {
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), aposta, tipoEntrada, padrao);
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    public void verificarApostaIntervaloEFullTime(List<Jogos> jogos, int homeScoreHalf, int awayScoreHalf, int homeScoreFull, int awayScoreFull, String padrao, String aposta, String tipoEntrada) {
        for (Jogos jogo : jogos) {
            try {
                if (jogo.getScoreHalfTime().getHome() == homeScoreHalf &&
                        jogo.getScoreHalfTime().getAway() == awayScoreHalf &&
                        jogo.getScoreFullTime().getHome() == homeScoreFull &&
                        jogo.getScoreFullTime().getAway() == awayScoreFull) {
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), aposta, tipoEntrada, padrao);
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadraoResultadosIguaisEntraQuinaOver25(List<Jogos> jogos) {
        for(int j = 1; j < jogos.size(); j++){
            int n = j;
            Jogos primeiroJogo = jogos.get(j);
            Jogos segundoJogo = jogos.get(n-1);

            try {
                if(primeiroJogo.getLiga().equals(segundoJogo.getLiga())){
                    if(primeiroJogo.getScoreFullTime().getHome() == segundoJogo.getScoreFullTime().getHome()
                            && primeiroJogo.getScoreFullTime().getAway() == segundoJogo.getScoreFullTime().getAway()){
                        salvarCorretamenteNoBanco(primeiroJogo.getHour(), primeiroJogo.getMinute(), primeiroJogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_RESULTADOSIGUAIS_JOGOTODO_QUINA);
                    }
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    public void salvarCorretamenteNoBanco(int hour, int minute, String nomeLiga, String aposta, String tipoEntrada, String padrao)  {

        LocalTime lt = LocalTime.of(hour,minute,0);


        int m1 = 0;
        int m2 = 0;
        int m3 = 0;
        int m4 = 0;

        if(tipoEntrada.equals(TipoEntrada.QUINA)){
            lt = lt.plusHours(1);
            m1 = lt.plusMinutes(3).getMinute();
            m2 = lt.plusMinutes(6).getMinute();
            m3 = lt.plusMinutes(9).getMinute();
        }else if(tipoEntrada.equals(TipoEntrada.CIMA_MAIS_UM)){
            lt = lt.plusHours(1);
            m1 = lt.getMinute();
            m2 = lt.plusMinutes(3).getMinute();
            m3 = lt.plusMinutes(6).getMinute();
        }else if(tipoEntrada.equals(TipoEntrada.QUINTO_JOGO)){
            m1 = lt.plusMinutes(15).getMinute();
            m2 = lt.plusMinutes(18).getMinute();
            m3 = lt.plusMinutes(21).getMinute();
            lt = lt.plusMinutes(21);
        }else if(tipoEntrada.equals(TipoEntrada.QUINTO_JOGO_HORA_ANTERIOR)){
            lt = lt.plusHours(1);
            m1 = lt.plusMinutes(15).getMinute();
            m2 = lt.plusMinutes(18).getMinute();
            m3 = lt.plusMinutes(21).getMinute();
        }

        lt = lt.minusMinutes(3);

        hour = lt.getHour();

        String liga = nomeLiga.toUpperCase(Locale.ROOT)  ;
        String hora = String.valueOf(hour);
        String minutos = m1 + "-" + m2 + "-" + m3;

        VerificarEntradas entrada = new VerificarEntradas();
        entrada.setLiga(liga);
        entrada.setHora(hora);
        entrada.setMinutos(minutos);
        entrada.setAposta(aposta);
        entrada.setPadrao(padrao);
        entrada.setMinuto1(Long.valueOf(m1));
        entrada.setMinuto2(Long.valueOf(m2));
        entrada.setMinuto3(Long.valueOf(m3));

        if(verificarEntradasService.verificarEntradaDuplicada(entrada)){
            try {
                entrada.setIdMessage(0L);
                entrada.setFlagFinalizado(false);
                entrada.setFlagGrem(false);
                verificarEntradasService.salvarEntrada(entrada);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void verificarEntradas(List<VerificarEntradas> entradas) {
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

        estatisticas = createEstatisticas(entradasEuro, LigasEnum.EURO.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.EURO.getNome(),estatisticas);

        estatisticas = createEstatisticas(entradasCopa, LigasEnum.COPA.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.COPA.getNome(),estatisticas);

        estatisticas = createEstatisticas(entradasPremier, LigasEnum.PREMIER.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.PREMIER.getNome(),estatisticas);

        estatisticas = createEstatisticas(entradasSulAmerica, LigasEnum.SULAMERICA.getNomeOficial());
        salvarAnalisePadroes(entradas.get(0).getPadrao(), LigasEnum.SULAMERICA.getNome(),estatisticas);

        verificarEntradasService.limparBanco();

    }

    private void salvarAnalisePadroes(String padrao, String liga, Estatisticas estatisticas) {
        AnalisePadroes analisePadroes = new AnalisePadroes();
        analisePadroes.setPadrao(padrao);
        analisePadroes.setLiga(liga);
        analisePadroes.setIsPorcentagemBoa(Util.verificarPorcentagemPadrao(estatisticas.getTotal(), estatisticas.getContReds(), estatisticas.getContEntradas(), estatisticas.getContGrens()));
        analisePadroes.setEstatisticas(Util.estatisticaCompleto(estatisticas));
       try{
           analisePadroesService.salvarEntrada(analisePadroes);
       }catch (Exception e){
           return;
       }
    }

    public Estatisticas createEstatisticas(List<VerificarEntradas> entradas, String liga){
        if(entradas.isEmpty()){
            return new Estatisticas();
        }

        ApiVsStatsService pd = new ApiVsStatsService(liga);
        List<Jogos> ultimosJogos = pd.getLastGames();

        return logicaParaFazerUpdateDaEntrada(entradas,ultimosJogos);
    }

    public Estatisticas logicaParaFazerUpdateDaEntrada(List<VerificarEntradas> entradas, List<Jogos> ultimosJogos) {
        Estatisticas estatisticas = new Estatisticas();

        for (VerificarEntradas entrada : entradas) {
            List<Long> minutos = new ArrayList<>();
            minutos.add(entrada.getMinuto1());
            minutos.add(entrada.getMinuto2());
            minutos.add(entrada.getMinuto3());

            entrada.setFlagFinalizado(true);

            Boolean isEntradaQuebrada = Util.isEntradaQuebrada(entrada.getMinuto1(), entrada.getMinuto3());

            if(isEntradaQuebrada){
                for (Long minuto : minutos) {
                    Boolean isMinutoHoraAtualEntrada = Util.isMinutoHoraAtualEntrada(minuto);
                    for (Jogos jogo : ultimosJogos) {
                        try{
                            if(!entrada.getFlagGrem()){
                                if(isMinutoHoraAtualEntrada){
                                    logicaTipoAposta(entrada, jogo, minuto, Integer.valueOf(entrada.getHora()));
                                }else{
                                    logicaTipoAposta(entrada, jogo, minuto, Integer.valueOf(entrada.getHora()) + 1);
                                }
                            }

                        }catch (Exception e){continue;}
                    }
                }

            }else{
                for (Long minuto : minutos) {
                    for (Jogos jogo : ultimosJogos) {
                        try {
                            if (!entrada.getFlagGrem()) {
                                logicaTipoAposta(entrada, jogo, minuto, Integer.valueOf(entrada.getHora()));
                            }

                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }

            if (entrada.getFlagFinalizado()) {
                estatisticas.addEntradas(1L);
                if (entrada.getFlagGrem()) {
                    estatisticas.addGrens(1L);
                } else {
                    estatisticas.addRed(1L);
                }
            }
        }
        
        return estatisticas;
    }

    private static void logicaTipoAposta(VerificarEntradas entrada, Jogos jogo, Long minuto, Integer hora) {
        if (Integer.valueOf(hora) == jogo.getHour()) {
            if (jogo.getMinute() == minuto) {
                if(entrada.getAposta().equals(Apostas.EMPATEHT)){
                    if (jogo.getScoreHalfTime().getHome() == jogo.getScoreHalfTime().getAway()) {
                        entrada.setFlagGrem(true);
                        entrada.setFlagFinalizado(true);
                    }
                }

                if(entrada.getAposta().equals(Apostas.AMBASMARCAM)){
                    if (jogo.getScoreFullTime().getHome() > 0 && jogo.getScoreFullTime().getAway() > 0) {
                        entrada.setFlagGrem(true);
                        entrada.setFlagFinalizado(true);
                    }
                }

                if(entrada.getAposta().equals(Apostas.FORAMARCAUM)){
                    if (jogo.getScoreFullTime().getAway() == 1) {
                        entrada.setFlagGrem(true);
                        entrada.setFlagFinalizado(true);
                    }
                }

                if(entrada.getAposta().equals(Apostas.OVER25)){
                    if (jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway() >= 3) {
                        entrada.setFlagGrem(true);
                        entrada.setFlagFinalizado(true);
                    }
                }

                if(entrada.getAposta().equals(Apostas.CASA_VENCE)){
                    if (jogo.getScoreFullTime().getHome() > jogo.getScoreFullTime().getAway()) {
                        entrada.setFlagGrem(true);
                        entrada.setFlagFinalizado(true);
                    }
                }
            }
        }
    }
}
