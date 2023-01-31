package com.roboAnalises.service;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.Estatisticas;
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
        verificarPadrao0x0IntervaloParaOver25NaQuina(jogos);
        verificarPadrao2x2ParaOver25EmCima(jogos);
        verificarPadrao3x1CasaParaOver25EmCima(jogos);
        verificarPadrao1x3ForaParaOver25EmCima(jogos);
        verificarPadrao0x0Intervalo0x1JogoTodoParaOver25Quina(jogos);
        verificarPadrao0x0Intervalo1x0JogoTodoParaOver25Quina(jogos);
        verificarPadrao0x0Intervalo2x0JogoTodoParaOver25Quina(jogos);
        verificarPadrao0x1Intervalo0x1JogoTodoParaOver25Quina(jogos);
        verificarPadrao0x1Intervalo0x2JogoTodoParaOver25Quina(jogos);
        verificarPadrao1x0Intervalo2x0JogoTodoParaOver25Quina(jogos);
        verificarPadrao1x1Intervalo1x1JogoTodoParaOver25Quina(jogos);

        verficarPadrao1x1IntervaloParaAmbasNaQuina(jogos);
        verificarPadrao0x0IntervaloParaAmbasNaQuina(jogos);
        verficarPadrao2x0CasaParaAmbasNaQuina(jogos);
        verficarPadrao0x2ForaParaAmbasNaQuina(jogos);
        verficarPadrao0x0ParaAmbasNaQuina(jogos);
        verificarPadrao1x3ForaParaAmbasEmCima(jogos);
        verificarPadrao2x2ParaAmbasEmCima(jogos);
        verificarPadrao3x1CasaParaAmbasEmCima(jogos);
        verificarPadrao2x0CasaParaOver25Quinto(jogos);

    }

    private void verificarPadrao2x0CasaParaOver25Quinto(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 2 && jogo.getScoreFullTime().getAway() == 0){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINTO_JOGO, Padrao.OVER25_2x0_JOGOTODO_QUINTO);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao3x1CasaParaAmbasEmCima(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 3 && jogo.getScoreFullTime().getAway() == 1){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, Padrao.AMBAS_3x1_JOGOTODO_CIMA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao2x2ParaAmbasEmCima(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 2 && jogo.getScoreFullTime().getAway() == 2){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, Padrao.AMBAS_2x2_JOGOTODO_CIMA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao1x3ForaParaAmbasEmCima(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 1 && jogo.getScoreFullTime().getAway() == 3){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM, Padrao.AMBAS_1x3_JOGOTODO_CIMA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verficarPadrao0x0ParaAmbasNaQuina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 0 && jogo.getScoreFullTime().getAway() == 0){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.QUINA, Padrao.AMBAS_0x0_JOGOTODO_QUINA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verficarPadrao0x2ForaParaAmbasNaQuina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 0 && jogo.getScoreFullTime().getAway() == 2){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.QUINA, Padrao.AMBAS_0x2FORA_JOGOTODO_QUINA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verficarPadrao2x0CasaParaAmbasNaQuina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 2 && jogo.getScoreFullTime().getAway() == 0){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.QUINA, Padrao.AMBAS_2x0CASA_JOGOTODO_QUINA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao0x0IntervaloParaAmbasNaQuina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 0 && jogo.getScoreHalfTime().getAway() == 0){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.QUINA, Padrao.AMBAS_0x0_INTERVALO_QUINA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verficarPadrao1x1IntervaloParaAmbasNaQuina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 1 && jogo.getScoreHalfTime().getAway() == 1){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.AMBASMARCAM, TipoEntrada.QUINA, Padrao.AMBAS_1x1_INTERVALO_QUINA);

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

    private void verificarPadrao0x0IntervaloParaOver25NaQuina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 0 && jogo.getScoreHalfTime().getAway() == 0){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_0x0_INTERVALO_QUINA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao2x2ParaOver25EmCima(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 2 && jogo.getScoreFullTime().getAway() == 2){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, Padrao.OVER25_2x2_JOGOTODO_CIMA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao3x1CasaParaOver25EmCima(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 3 && jogo.getScoreFullTime().getAway() == 1){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, Padrao.OVER25_3x1_JOGOTODO_CIMA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao1x3ForaParaOver25EmCima(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreFullTime().getHome() == 1 && jogo.getScoreFullTime().getAway() == 3){
                    salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM, Padrao.OVER25_1x3_JOGOTODO_CIMA);

                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }


    private void verificarPadrao0x0Intervalo0x1JogoTodoParaOver25Quina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 0 && jogo.getScoreHalfTime().getAway() == 0){
                    if(jogo.getScoreFullTime().getHome() == 0 && jogo.getScoreFullTime().getAway() == 1) {
                        salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_0x0INTERVALO_0x1JOGOTODO_QUINA);
                    }
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }


    private void verificarPadrao0x0Intervalo1x0JogoTodoParaOver25Quina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 0 && jogo.getScoreHalfTime().getAway() == 0){
                    if(jogo.getScoreFullTime().getHome() == 1 && jogo.getScoreFullTime().getAway() == 0){
                        salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_0x0INTERVALO_1x0JOGOTODO_QUINA);
                    }
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }



    private void verificarPadrao0x0Intervalo2x0JogoTodoParaOver25Quina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 0 && jogo.getScoreHalfTime().getAway() == 0){
                    if(jogo.getScoreFullTime().getHome() == 2 && jogo.getScoreFullTime().getAway() == 0){
                        salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_0x0INTERVALO_2x0JOGOTODO_QUINA);
                    }
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }


    private void verificarPadrao0x1Intervalo0x1JogoTodoParaOver25Quina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 0 && jogo.getScoreHalfTime().getAway() == 1){
                    if(jogo.getScoreFullTime().getHome() == 0 && jogo.getScoreFullTime().getAway() == 1){
                        salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_0x1INTERVALO_0x1JOGOTODO_QUINA);
                    }
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }



    private void verificarPadrao0x1Intervalo0x2JogoTodoParaOver25Quina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 0 && jogo.getScoreHalfTime().getAway() == 1){
                    if(jogo.getScoreFullTime().getHome() == 0 && jogo.getScoreFullTime().getAway() == 2){
                        salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_0x1INTERVALO_0x2JOGOTODO_QUINA);
                    }
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }


    private void verificarPadrao1x0Intervalo2x0JogoTodoParaOver25Quina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 1 && jogo.getScoreHalfTime().getAway() == 0){
                    if(jogo.getScoreFullTime().getHome() == 2 && jogo.getScoreFullTime().getAway() == 0){
                        salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_1x0INTERVALO_2x0JOGOTODO_QUINA);
                    }
                }
            }catch (NullPointerException e){
                continue;
            }
        }
        verificarEntradas(verificarEntradasService.findAllEntradas());
    }

    private void verificarPadrao1x1Intervalo1x1JogoTodoParaOver25Quina(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            try{
                if(jogo.getScoreHalfTime().getHome() == 1 && jogo.getScoreHalfTime().getAway() == 1){
                    if(jogo.getScoreFullTime().getHome() == 1 && jogo.getScoreFullTime().getAway() == 1){
                        salvarCorretamenteNoBanco(jogo.getHour(), jogo.getMinute(), jogo.getLiga(), Apostas.OVER25, TipoEntrada.QUINA, Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_QUINA);
                    }
                }
            }catch (NullPointerException e){
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
        }else{
            m1 = lt.plusMinutes(15).getMinute();
            m2 = lt.plusMinutes(18).getMinute();
            m3 = lt.plusMinutes(21).getMinute();
            lt = lt.plusMinutes(21);
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
        entrada.setMinuto1(m1);
        entrada.setMinuto2(m2);
        entrada.setMinuto3(m3);

        if(verificarEntradasService.verificarEntradaDuplicada(entrada)){
            try {
                entrada.setIdMessage(0);
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
        analisePadroesService.salvarEntrada(analisePadroes);
    }

    public Estatisticas createEstatisticas(List<VerificarEntradas> entradas, String liga){
        if(entradas.isEmpty()){
            return new Estatisticas();
        }

        ApiVsStatsService pd = new ApiVsStatsService(liga);
        List<Jogos> ultimosJogos = pd.getLastGames();

        return logicaParaFazerUpdateDaEntrada(entradas,ultimosJogos);
    }

    private Estatisticas logicaParaFazerUpdateDaEntrada(List<VerificarEntradas> entradas, List<Jogos> ultimosJogos) {
        Estatisticas estatisticas = new Estatisticas();

        for (VerificarEntradas entrada : entradas) {
            List<Integer> minutos = new ArrayList<>();
            minutos.add(entrada.getMinuto1());
            minutos.add(entrada.getMinuto2());
            minutos.add(entrada.getMinuto3());

            entrada.setFlagFinalizado(true);

            Boolean isEntradaQuebrada = Util.isEntradaQuebrada(entrada.getMinuto1(), entrada.getMinuto3());

            if(isEntradaQuebrada){
                for (Integer minuto : minutos) {
                    Boolean isMinutoHoraAtualEntrada = Util.isMinutoHoraAtualEntrada(Integer.valueOf(minuto));
                    for (Jogos jogo : ultimosJogos) {
                        try{
                            if(!entrada.getFlagGrem()){
                                if(isMinutoHoraAtualEntrada){
                                    logicaTipoAposta(entrada, jogo, minuto, entrada.getHora());
                                }else{
                                    logicaTipoAposta(entrada, jogo, minuto, entrada.getHora() + 1);
                                }
                            }

                        }catch (Exception e){continue;}
                    }
                }

            }else{
                for (Integer minuto : minutos) {
                    for (Jogos jogo : ultimosJogos) {
                        try {
                            if (!entrada.getFlagGrem()) {
                                logicaTipoAposta(entrada, jogo, minuto, entrada.getHora());
                            }

                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }

            if (entrada.getFlagFinalizado()) {
                estatisticas.addEntradas(1);
                if (entrada.getFlagGrem()) {
                    estatisticas.addGrens(1);
                } else {
                    estatisticas.addRed(1);
                }
            }
        }
        
        return estatisticas;
    }

    private static void logicaTipoAposta(VerificarEntradas entrada, Jogos jogo, Integer minuto, String hora) {
        if (Integer.valueOf(hora) == jogo.getHour()) {
            if (jogo.getMinute() == Integer.valueOf(minuto)) {
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
