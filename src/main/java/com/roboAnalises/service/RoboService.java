package com.roboAnalises.service;

import com.roboAnalises.domain.AnalisePadroes;
import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.enums.Padrao;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Data;
import com.roboAnalises.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class RoboService {

    @Autowired
    EntradasService entradasService;

    @Autowired
    AnalisePadroesService analisePadroesService;

    @Autowired
    TelegramService telegramService;

    public void iniciarRobo(){

        //logica para so mandar uma entrada de cada vez
//        Long entradasNaoFinalizadas = entradasService.findEntradasNaoFinalizadas();
//        if(entradasNaoFinalizadas >= 1){
//            return;
//        }

        List<AnalisePadroes> analisePadroes = analisePadroesService.getAnalisePadroes();

        if(analisePadroes.isEmpty()) {
            return;
        }

        verificarPadrao(analisePadroes);
    }

    public void verificarPadrao(List<AnalisePadroes> analisePadroes){
        List<Jogos> ultimosjogos = new ApiVsStatsService().getUltimosJogosTodasLigas();

        if(ultimosjogos.isEmpty()){
            System.out.println("Token Expirou");
        }

        ultimosjogos.removeIf(j -> {
            int hora = LocalTime.of(Data.getHoraAtualLondon(), 0, 0).minusHours(1).getHour();
            return j.getHour() != hora ;
        });

        Collections.shuffle(ultimosjogos);


        for(int i = ultimosjogos.size()-1; i >= 0; i--){
            try{
                if (ultimosjogos.get(i).getMinute() > Data.getMinutoAtualLondon()+4 && ultimosjogos.get(i).getMinute() < Data.getMinutoAtualLondon() + 8) {
                    for (AnalisePadroes analise : analisePadroes) {
//                        Long entradasNaoFinalizadas = entradasService.findEntradasNaoFinalizadas();
//                        if(entradasNaoFinalizadas >= 1){
//                            return;
//                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_RESULTADOSIGUAIS_JOGOTODO_QUINA)){
                            if(ultimosjogos.get(i).getLiga().equals(ultimosjogos.get(i+1).getLiga())){
                                verificarOver25ResultadosIguais(ultimosjogos.get(i), ultimosjogos.get(i+1), analise.getLiga());
                            }
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_0x0_INTERVALO_QUINA)){
                            verificarOver250x0Intervalo(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_2x2_JOGOTODO_CIMA)){
                            verificarOver252x2(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_3x1_JOGOTODO_CIMA)){
                            verificarOver253x1Casa(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_1x3_JOGOTODO_CIMA)){
                            verificarOver251x3Fora(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_0x0INTERVALO_0x1JOGOTODO_QUINA)){
                            verificarOver250x0Intervalo0x1JogoTodoQuina(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_0x0INTERVALO_1x0JOGOTODO_QUINA)){
                            verificarOver250x0Intervalo1x0JogoTodoQuina(ultimosjogos.get(i), analise.getLiga());
                        }
                        if(analise.getPadrao().equals(Padrao.OVER25_0x0INTERVALO_2x0JOGOTODO_QUINA)){
                            verificarOver250x0Intervalo2x0JogoTodoQuina(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_0x1INTERVALO_0x1JOGOTODO_QUINA)){
                            verificarOver250x1Intervalo0x1JogoTodoQuina(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_0x1INTERVALO_0x2JOGOTODO_QUINA)){
                            verificarOver250x1Intervalo0x2JogoTodoQuina(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_1x0INTERVALO_2x0JOGOTODO_QUINA)){
                            verificarOver251x0Intervalo2x0JogoTodoQuina(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_1x1INTERVALO_1x1JOGOTODO_QUINA)){
                            verificarOver251x1Intervalo1x1JogoTodoQuina(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_1x1_INTERVALO_QUINA)){
                            verificarAmbasMarcam1x1Intervalo(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_0x0_INTERVALO_QUINA)){
                            verificarAmbasMarcam0x0Intervalo(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_2x0CASA_JOGOTODO_QUINA)){
                            verificarAmbasMarcam2x0Casa(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_0x2FORA_JOGOTODO_QUINA)){
                            verificarAmbasMarcam0x2Visitante(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_0x0_JOGOTODO_QUINA)){
                            verificarAmbasMarcam0x0(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_3x1_JOGOTODO_CIMA)){
                            verificarAmbas3x1Casa(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_2x2_JOGOTODO_CIMA)){
                            verificarAmbas2x2(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.AMBAS_1x3_JOGOTODO_CIMA)){
                            verificarAmbas1x3Fora(ultimosjogos.get(i), analise.getLiga());
                        }

                        if(analise.getPadrao().equals(Padrao.OVER25_2x0_JOGOTODO_QUINTO)){
                            verificarOver252x0CasaQuinto(ultimosjogos.get(i), analise.getLiga());
                        }





                    }
                }

            }catch (Exception e){
                continue;
            }
        }
    }

    private void verificarOver252x0CasaQuinto(Jogos jogo, String liga) {
        if (jogo.getLiga().equals(liga)) {
            if(jogo.getScoreFullTime().getHome() == 2) {
                if (jogo.getScoreFullTime().getAway() == 0) {
                    telegramService.montarMensagem(jogo, Apostas.OVER25, TipoEntrada.QUINTO_JOGO);
                }
            }
        }
    }

    private void verificarAmbas1x3Fora(Jogos jogo, String liga) {
        if (jogo.getLiga().equals(liga)) {
            if(jogo.getScoreFullTime().getHome() == 1) {
                if (jogo.getScoreFullTime().getAway() == 3) {
                    telegramService.montarMensagem(jogo, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
                }
            }
        }
    }

    private void verificarAmbas2x2(Jogos jogo, String liga) {
        if (jogo.getLiga().equals(liga)) {
            if(jogo.getScoreFullTime().getHome() == 2) {
                if (jogo.getScoreFullTime().getAway() == 2) {
                    telegramService.montarMensagem(jogo, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
                }
            }
        }
    }

    private void verificarAmbas3x1Casa(Jogos jogo, String liga) {
        if (jogo.getLiga().equals(liga)) {
            if(jogo.getScoreFullTime().getHome() == 3) {
                if (jogo.getScoreFullTime().getAway() == 1) {
                    telegramService.montarMensagem(jogo, Apostas.AMBASMARCAM, TipoEntrada.CIMA_MAIS_UM);
                }
            }
        }
    }

    private void verificarAmbasMarcam0x0(Jogos jogo, String liga) {
        if (jogo.getLiga().equals(liga)) {
            if(jogo.getScoreFullTime().getHome() == 0){
                if(jogo.getScoreFullTime().getAway() == 0){
                    telegramService.montarMensagem(jogo, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
                }
            }
        }
    }

    private void verificarAmbasMarcam0x2Visitante(Jogos jogo, String liga) {
        if (jogo.getLiga().equals(liga)) {
            if(jogo.getScoreFullTime().getHome() == 0){
                if(jogo.getScoreFullTime().getAway() == 2){
                    telegramService.montarMensagem(jogo, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
                }
            }
        }
    }

    private void verificarAmbasMarcam2x0Casa(Jogos jogo, String liga) {
        if (jogo.getLiga().equals(liga)) {
            if(jogo.getScoreFullTime().getHome() == 2){
                if(jogo.getScoreFullTime().getAway() == 0){
                    telegramService.montarMensagem(jogo, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
                }
            }
        }
    }

    private void verificarAmbasMarcam0x0Intervalo(Jogos jogos, String liga) {
        if (jogos.getLiga().equals(liga)) {
            if(jogos.getScoreHalfTime().getHome() == 0) {
                if (jogos.getScoreHalfTime().getAway() == 0) {
                    telegramService.montarMensagem(jogos, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
                }
            }
        }
    }

    private void verificarAmbasMarcam1x1Intervalo(Jogos jogos, String liga) {
        if (jogos.getLiga().equals(liga)) {
            if(jogos.getScoreHalfTime().getHome() == 1) {
                if (jogos.getScoreHalfTime().getAway() == 1) {
                    telegramService.montarMensagem(jogos, Apostas.AMBASMARCAM, TipoEntrada.QUINA);
                }
            }
        }
    }

    private void verificarOver251x1Intervalo1x1JogoTodoQuina(Jogos jogos, String liga) {
        if (jogos.getLiga().equals(liga)) {
            if(jogos.getScoreHalfTime().getHome() == 1){
                if(jogos.getScoreHalfTime().getAway() == 1){
                    if(jogos.getScoreFullTime().getHome() == 1) {
                        if (jogos.getScoreFullTime().getAway() == 1) {
                            telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.QUINA);
                        }
                    }
                }
            }
        }
    }

    private void verificarOver251x0Intervalo2x0JogoTodoQuina(Jogos jogos, String liga) {
        if (jogos.getLiga().equals(liga)) {
            if(jogos.getScoreHalfTime().getHome() == 1){
                if(jogos.getScoreHalfTime().getAway() == 0){
                    if(jogos.getScoreFullTime().getHome() == 2) {
                        if (jogos.getScoreFullTime().getAway() == 0) {
                            telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.QUINA);
                        }
                    }
                }
            }
        }
    }

    private void verificarOver250x1Intervalo0x2JogoTodoQuina(Jogos jogos, String liga) {
        if (jogos.getLiga().equals(liga)) {
            if(jogos.getScoreHalfTime().getHome() == 0){
                if(jogos.getScoreHalfTime().getAway() == 1){
                    if(jogos.getScoreFullTime().getHome() == 0) {
                        if (jogos.getScoreFullTime().getAway() == 2) {
                            telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.QUINA);
                        }
                    }
                }
            }
        }
    }

    private void verificarOver250x1Intervalo0x1JogoTodoQuina(Jogos jogos, String liga) {
        if (jogos.getLiga().equals(liga)) {
            if(jogos.getScoreHalfTime().getHome() == 0){
                if(jogos.getScoreHalfTime().getAway() == 1){
                    if(jogos.getScoreFullTime().getHome() == 0) {
                        if (jogos.getScoreFullTime().getAway() == 1) {
                            telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.QUINA);
                        }
                    }
                }
            }
        }
    }

    private void verificarOver250x0Intervalo2x0JogoTodoQuina(Jogos jogos, String liga) {
    if(jogos.getLiga().equals(liga)){
        if(jogos.getScoreHalfTime().getHome() == 0){
            if(jogos.getScoreHalfTime().getAway() == 0){
                if(jogos.getScoreFullTime().getHome() == 2) {
                    if (jogos.getScoreFullTime().getAway() == 0) {
                        telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.QUINA);
                    }
                }}}
    }
}
    private void verificarOver250x0Intervalo1x0JogoTodoQuina(Jogos jogos, String liga) {
        if(jogos.getLiga().equals(liga)){
            if(jogos.getScoreHalfTime().getHome() == 0){
                if(jogos.getScoreHalfTime().getAway() == 0){
                    if(jogos.getScoreFullTime().getHome() == 1) {
                        if (jogos.getScoreFullTime().getAway() == 0) {
                    telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.QUINA);
                }
            }}}
        }
    }

    private void verificarOver250x0Intervalo0x1JogoTodoQuina(Jogos jogos, String liga) {
        if(jogos.getLiga().equals(liga)){
            if(jogos.getScoreHalfTime().getHome() == 0){
                if(jogos.getScoreHalfTime().getAway() == 0){
                    if(jogos.getScoreFullTime().getHome() == 0) {
                        if (jogos.getScoreFullTime().getAway() == 1) {
                            telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.QUINA);
                }
            }
        }
    }
        }}

    private void verificarOver251x3Fora(Jogos jogos, String liga) {
        if(jogos.getLiga().equals(liga)){
            if(jogos.getScoreFullTime().getHome() == 1) {
                if (jogos.getScoreFullTime().getAway() == 3) {
                    telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM);
                }
            }
        }
    }

    private void verificarOver253x1Casa(Jogos jogos, String liga) {
        if(jogos.getLiga().equals(liga)){
            if(jogos.getScoreFullTime().getHome() == 3) {
                if (jogos.getScoreFullTime().getAway() == 1) {
                    telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM);
                }
            }
        }
    }

    private void verificarOver252x2(Jogos jogos, String liga) {
        if(jogos.getLiga().equals(liga)){
            if(jogos.getScoreFullTime().getHome() == 2) {
                if (jogos.getScoreFullTime().getAway() == 2) {
                    telegramService.montarMensagem(jogos, Apostas.OVER25, TipoEntrada.CIMA_MAIS_UM);
                }
            }
        }
    }

    private void verificarOver250x0Intervalo(Jogos jogo, String ligaAnalise) {
        if(jogo.getLiga().equals(ligaAnalise)){
            if(jogo.getScoreHalfTime().getHome() == 0) {
                if (jogo.getScoreHalfTime().getAway() == 0) {
                    telegramService.montarMensagem(jogo, Apostas.OVER25, TipoEntrada.QUINA);
                }
            }
        }
    }

    private void verificarOver25ResultadosIguais(Jogos primeiroJogo, Jogos segundoJogo, String ligaAnalise) {
        if(primeiroJogo.getLiga().equals(ligaAnalise)){
            if(primeiroJogo.getScoreFullTime().getHome() == segundoJogo.getScoreFullTime().getHome() && primeiroJogo.getScoreFullTime().getAway() == segundoJogo.getScoreFullTime().getAway()) {
                telegramService.montarMensagem(primeiroJogo, Apostas.OVER25, TipoEntrada.QUINA);
            }
        }
    }
}
