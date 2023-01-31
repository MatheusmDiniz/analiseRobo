package com.roboAnalises.service;

import com.roboAnalises.domain.Informacoes;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformacoesService {

    public StringBuilder getMediaOverUltimas8Linhas(LigasEnum liga, List<Jogos> jogos){
        List<Informacoes> informacoes = new ArrayList<>();

        for (Integer hora : Data.getUltimas3HorasLondres()) {
                Informacoes info = new Informacoes();
                info.setHora(hora);
                for (Jogos jogo : jogos) {
                    if(jogo.getHour() == hora){
                        if((jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway()) >= 3){
                            info.addQuantidadeOver25(1);
                        }
                    }
                }
                informacoes.add(info);
            }

        Double media = Double.valueOf(0);
        for (Informacoes informacoe : informacoes) {
            media += informacoe.getQuantidadeOver25();
        }

        media = media/informacoes.size();


            StringBuilder sb = new StringBuilder();

            sb.append("\nMedia Over 2.5 nas ultimas 3 horas: " + media);
            sb.append("\n");
            return sb;

    }

    public StringBuilder getMediaUltimasLinhasEPorQuadrante(LigasEnum liga, List<Jogos> jogos){


        Integer gols = 0;
        for (Integer hora : Data.getUltimas3HorasLondres()) {
            for (Jogos jogo : jogos) {
                if(jogo.getHour() == hora){
                    gols += jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway();
                }
            }
        }

        gols = (gols/3)/4;

        StringBuilder sb = new StringBuilder();

        sb.append("\nMedia de Gols Por quadrante nas ultimas 3 horas: "+gols);
        sb.append("\n");
        return sb;
    }

    public StringBuilder getMelhoresLigas(){
        List<Jogos> jogos = new ApiVsStatsService().getUltimosJogosTodasLigas();

        List<Informacoes> informacoes = new ArrayList<>();

        for (String liga : LigasEnum.getLigas()) {
            Informacoes info = new Informacoes();
            info.setLiga(liga);
            for (Integer hora : Data.getUltimas10HorasLondres()) {
                for (Jogos jogo : jogos) {
                    if(jogo.getHour() == hora && jogo.getLiga().equals(info.getLiga())){
                        if((jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway()) >= 3){
                            info.addQuantidadeOver25(1);
                        }
                    }
                }
            }
            informacoes.add(info);
        }

        informacoes.sort((c1, c2) -> Integer.compare(c2.getQuantidadeOver25(), c1.getQuantidadeOver25()));

        StringBuilder sb = new StringBuilder();
        sb.append("\nOrdem das melhores Ligas para Over 2.5: \n");
        for (Informacoes informacoe : informacoes) {
            sb.append(informacoe.getLiga() + ": "+informacoe.getQuantidadeOver25()+"\n");
        }

        return sb;
    }
}
