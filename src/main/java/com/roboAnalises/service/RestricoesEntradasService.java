package com.roboAnalises.service;

import com.roboAnalises.domain.MediaMinutosHoras;
import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static boolean returnTrueIfAMediaDosMinutosDaEntradaForRuim(List<Jogos> ultimosJogosTemp, String liga, String aposta, String m1) {
        List<Jogos> jogos = new ArrayList<>();
        ultimosJogosTemp.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            Boolean mesmaLiga = false;
            Boolean mesmosMinutos = false;

            if(j.getLiga().equals(liga)){
                mesmaLiga = true;
            }

            if(j.getMinute() == Integer.valueOf(m1)){
                mesmosMinutos = true;
            }

            if(mesmosMinutos){
                if(mesmaLiga){
                    return false;
                }
            }

            return true;
        });

        int countGrem = 0;
        int countRed = 0;

        List<MediaMinutosHoras> separacaoHoras = new ArrayList<>();

        for (Jogos jogo : jogos) {
            MediaMinutosHoras mediaMinutosHoras = new MediaMinutosHoras();
            mediaMinutosHoras.setHora(jogo.getHour());
            mediaMinutosHoras.setM1(Integer.parseInt(m1));
            mediaMinutosHoras.setM2(Integer.parseInt(m1)+3);
            mediaMinutosHoras.setM3(Integer.parseInt(m1)+6);

            separacaoHoras.add(mediaMinutosHoras);
        }

        jogos.clear();
        ultimosJogosTemp.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            Boolean mesmaLiga = false;
            Boolean mesmosMinutos = false;

            if(j.getLiga().equals(liga)){
                mesmaLiga = true;
            }

            if(j.getMinute() == Integer.valueOf(m1) || j.getMinute() == Integer.valueOf(m1)+3 || j.getMinute() == Integer.valueOf(m1)+6){
                mesmosMinutos = true;
            }

            if(mesmosMinutos){
                if(mesmaLiga){
                    return false;
                }
            }

            return true;
        });



        for (MediaMinutosHoras separacaoHora : separacaoHoras) {
            int countRedsJogo = 0;
            for (Jogos jogo : jogos) {
                if(jogo.getHour() == separacaoHora.getHora()){
                    if(jogo.getMinute() == separacaoHora.getM1() || jogo.getMinute() == separacaoHora.getM2() || jogo.getMinute() == separacaoHora.getM3()){
                        if(aposta.equals(Apostas.AMBASMARCAM)){
                            if(jogo.getScoreFullTime().getHome() < 1 || jogo.getScoreFullTime().getAway() < 1){
                                countRedsJogo++;
                            }
                        }
                        if(aposta.equals(Apostas.OVER25)) {
                            if ((jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway()) <= 2) {
                                countRedsJogo++;
                            }
                        }
                    }
                }
            }

            if(countRedsJogo == 3){
                countRed++;
            }else{
                countGrem++;
            }
        }


        if(countGrem >=19){
            return false;
        }



        return true;



    }
}
