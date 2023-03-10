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

    public Double getMediaOverUltimas3Horas(LigasEnum liga, List<Jogos> jogosOld){
        List<Jogos> jogos = new ArrayList<>();
        jogosOld.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            return Data.verificarDiaHoraJogoComLondresMenos3CasoDiferenteRetornaTrue(j.getIdString());
        });

        List<Informacoes> informacoes = new ArrayList<>();

        int totalJogos = 0;
        int ambas = 0;
                Informacoes info = new Informacoes();
                for (Jogos jogo : jogos) {
                    if (jogo.getLiga().equals(liga.getNome())) {
                        if ((jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway()) >= 3) {
                            ambas++;
                        }
                        totalJogos++;
                    }
                    informacoes.add(info);
                }
        Double media = Double.valueOf(ambas/3);


                return media;
//            StringBuilder sb = new StringBuilder();
//
//            sb.append("\nMedia Over 2.5 nas ultimas 3 horas: " + media);
//            sb.append("\n");
//            return sb;

    }

    public Double getMediaAmbasUltimas3Horas(LigasEnum liga, List<Jogos> jogosOld){
        List<Jogos> jogos = new ArrayList<>();
        jogosOld.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            return Data.verificarDiaHoraJogoComLondresMenos3CasoDiferenteRetornaTrue(j.getIdString());
        });

        List<Informacoes> informacoes = new ArrayList<>();

        int totalJogos = 0;
        int ambas = 0;
        Informacoes info = new Informacoes();
        for (Jogos jogo : jogos) {
            if (jogo.getLiga().equals(liga.getNome())) {
                if(jogo.getScoreFullTime().getHome() >=1 && jogo.getScoreFullTime().getAway() >= 1){
                    ambas++;
                }
                totalJogos++;
            }
            informacoes.add(info);
        }
        Double media = Double.valueOf(ambas/3);

        return media;

//        StringBuilder sb = new StringBuilder();
//
//        sb.append("\nMedia Ambas nas ultimas 3 horas: " + media);
//        sb.append("\n");
//        return sb;

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

    public StringBuilder getMelhoresLigasOver25Ultimas10Horas(List<Jogos> jogosOld){
        List<Jogos> jogos = new ArrayList<>();
        jogosOld.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            return Data.verificarDiaJogoComLondresCasoDiferenteRetornaTrue(j.getIdString());
        });

        List<Informacoes> informacoes = new ArrayList<>();

        for (String liga : LigasEnum.getLigas()) {
            Informacoes info = new Informacoes();
            info.setLiga(liga);
                for (Jogos jogo : jogos) {
                    if(jogo.getLiga().equals(info.getLiga())){
                        if((jogo.getScoreFullTime().getHome() + jogo.getScoreFullTime().getAway()) >= 3){
                            info.addQuantidadeOver25(1L);
                        }
                    }
                }
            informacoes.add(info);
        }

        informacoes.sort((c1, c2) -> Long.compare(c2.getQuantidadeOver25(), c1.getQuantidadeOver25()));

        StringBuilder sb = new StringBuilder();
        sb.append("\nOrdem das melhores Ligas para Over 2.5: \n");
        for (Informacoes informacoe : informacoes) {
            sb.append(informacoe.getLiga() + ": "+informacoe.getQuantidadeOver25()+"\n");
        }


        return sb;
    }

    public StringBuilder getMelhoresLigasAmbasUltimas10Horas(List<Jogos> jogosOld){
        List<Jogos> jogos = new ArrayList<>();
        jogosOld.forEach(j -> {
            jogos.add(j);
        });

        jogos.removeIf(j -> {
            return Data.verificarDiaJogoComLondresCasoDiferenteRetornaTrue(j.getIdString());
        });

        List<Informacoes> informacoes = new ArrayList<>();

        for (String liga : LigasEnum.getLigas()) {
            Informacoes info = new Informacoes();
            info.setLiga(liga);
                for (Jogos jogo : jogos) {
                    if(jogo.getLiga().equals(info.getLiga())){
                        if(jogo.getScoreFullTime().getHome() >=1 && jogo.getScoreFullTime().getAway() >= 1){
                            info.addQuantidadeOver25(1L);
                        }
                    }
                }
            informacoes.add(info);
        }

        informacoes.sort((c1, c2) -> Long.compare(c2.getQuantidadeOver25(), c1.getQuantidadeOver25()));

        StringBuilder sb = new StringBuilder();
        sb.append("\nOrdem das melhores Ligas para Ambas: \n");
        for (Informacoes informacoe : informacoes) {
            sb.append(informacoe.getLiga() + ": "+informacoe.getQuantidadeOver25()+"\n");
        }

        return sb;
    }
}
