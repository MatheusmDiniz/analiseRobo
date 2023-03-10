package com.roboAnalises.service;

import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Data;

import java.util.ArrayList;
import java.util.List;

public class RestricoesEntradasService {
    public static boolean returnTrueIfVerificarMais3RedsSeguidosUltimaHora(List<Jogos> ultimos200Jogos, String aposta) {
        List<Jogos> jogos = new ArrayList<>();
        ultimos200Jogos.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            return Data.filtraApenasJogosUltimaHora(j.getIdString());
        });

        int contReds = 0;

        for (Jogos jogo : jogos) {
            if(aposta.equals(Apostas.AMBASMARCAM)){
                if(jogo.getScoreFullTime().getHome() > 0 && jogo.getScoreFullTime().getAway() > 0){
                    contReds = 0;
                }else{
                    contReds++;
                }
            }

            if(aposta.equals(Apostas.OVER25)){
                if((jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway()) > 2){
                    contReds = 0;
                }else{
                    contReds++;
                }
            }

            if (contReds == 3){
                return true;
            }
        }

        return false;

    }

    public static boolean returnTrueIfVerificarHoraComMaisOverOuAmbasQueAMedia(List<Jogos> ultimos200Jogos, String aposta, Double mediaOver25, Double mediaAmbas) {
        List<Jogos> jogos = new ArrayList<>();
        ultimos200Jogos.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            return Data.filtroJogosHoraAtual(j.getIdString());
        });

        int contQuantidadesGrens = 0;

        for (Jogos jogo : jogos) {
            if(aposta.equals(Apostas.AMBASMARCAM)){
                if(jogo.getScoreFullTime().getHome() > 0 && jogo.getScoreFullTime().getAway() > 0){
                    contQuantidadesGrens ++;
                }

                if (contQuantidadesGrens >= mediaAmbas){
                    return true;
                }
            }

            if(aposta.equals(Apostas.OVER25)){
                if((jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway()) > 2){
                    contQuantidadesGrens ++;
                }
                if (contQuantidadesGrens >= mediaOver25){
                    return true;
                }
            }


        }

        return false;
    }
}
