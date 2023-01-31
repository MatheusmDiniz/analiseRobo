package com.roboAnalises.service;

import com.roboAnalises.domain.Classificacao;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.repository.ClassificacaoRepository;
import com.roboAnalises.domain.vstats.Jogos;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ClassificacaoService {
    @Autowired
    ClassificacaoRepository classificacaoRepository;

    public StringBuilder getTimesOver25AndAmbasMarcam(LigasEnum liga){
        List<Jogos> jogos = new ApiVsStatsService(liga.getNomeOficial()).getLastGames();
        List<Classificacao> classificacaos = classificacaoRepository.findAllByLiga(liga.getNome());


        if(classificacaos.isEmpty()){
            classificacaos = getNomeTimes(liga, jogos, classificacaos);
        }else{
            classificacaos.forEach(c -> c.reseta());
        }

        classificacaos = separarESalvarClassificacao(jogos, classificacaos);

        Double mediaOver25 = Double.valueOf(0);
        Double mediaAmbas = Double.valueOf(0);
        for (Classificacao classificacao : classificacaos) {
            mediaOver25 += classificacao.getQuantidadeOver25();
            mediaAmbas += classificacao.getAmbasMarcam();
        }

        mediaOver25 = (mediaOver25/classificacaos.size());

//        mediaOver25 = mediaOver25 + mediaOver25/4;

        StringBuilder sb = new StringBuilder();
        sb.append("TIMES ACIMA DA MEDIA OVER 2.5");
        sb.append("\n");
        classificacaos.sort((c1, c2) -> Integer.compare(c2.getQuantidadeOver25(), c1.getQuantidadeOver25()));
        for (Classificacao classificacao : classificacaos) {
            if(classificacao.getQuantidadeOver25() > mediaOver25){
                sb.append(classificacao.getTime() + " - " + classificacao.getQuantidadeOver25());
                sb.append("\n");
            }
        }

        mediaAmbas = (mediaAmbas/classificacaos.size());

        classificacaos.sort((c1, c2) -> Integer.compare(c2.getAmbasMarcam(), c1.getAmbasMarcam()));
//        mediaAmbas = mediaAmbas + mediaAmbas/4;
        sb.append("\n");
        sb.append("TIMES ACIMA DA MEDIA DE AMBAS MARCAM");
        sb.append("\n");
        for (Classificacao classificacao : classificacaos) {
            if(classificacao.getAmbasMarcam() > mediaAmbas){
                sb.append(classificacao.getTime() + " - " + classificacao.getAmbasMarcam());
                sb.append("\n");
            }
        }

        return sb;
    }

    private List<Classificacao> separarESalvarClassificacao(List<Jogos> jogos, List<Classificacao> classificacaos) {
        for (Jogos jogo : jogos) {
            for (Classificacao classificacao : classificacaos) {
                if(jogo.getHome().getName().equals(classificacao.getTime()) || jogo.getAway().getName().equals(classificacao.getTime())){
                    if(jogo.getScoreFullTime().getAway() + jogo.getScoreFullTime().getHome() >= 3){
                        classificacao.setQuantidadeOver25(1);
                    }else{
                        classificacao.setQuantidadeUnder25(1);
                    }
                    if(jogo.getScoreFullTime().getAway() >= 1 && jogo.getScoreFullTime().getHome() >= 1){
                        classificacao.setAmbasMarcam(1);
                    }

                    if(jogo.getHome().getName().equals(classificacao.getTime())){
                        classificacao.setGolsFeitos(jogo.getScoreFullTime().getHome());
                        classificacao.setGolsLevados(jogo.getScoreFullTime().getAway());
                    }else{
                        classificacao.setGolsFeitos(jogo.getScoreFullTime().getAway());
                        classificacao.setGolsLevados(jogo.getScoreFullTime().getHome());
                    }
                }
            }
        }


        classificacaoRepository.saveAll(classificacaos);

        return classificacaos;
    }

    private static List<Classificacao> getNomeTimes(LigasEnum liga, List<Jogos> jogos, List<Classificacao> classificacaos) {
        Set<String> nomeTimes = new LinkedHashSet<>();

        for (Jogos jogo : jogos) {
            nomeTimes.add(jogo.getHome().getName());
            nomeTimes.add(jogo.getAway().getName());
        }

        for (String nome : nomeTimes) {
            Classificacao c = new Classificacao();
            c.setTime(nome);
            c.setLiga(liga.getNome());
            classificacaos.add(c);
        }
        return classificacaos;
    }

//    public StringBuilder getTimesAmbas(LigasEnum liga) {
//        List<Jogos> jogos = new ApiVsStatsService(liga.getNomeOficial()).getLastGames();
//        List<Classificacao> classificacaos = classificacaoRepository.findAllByLiga(liga.getNome());
//
//
//        if(classificacaos.isEmpty()){
//            classificacaos = getNomeTimes(liga, jogos, classificacaos);
//        }else{
//            classificacaos.forEach(c -> c.reseta());
//        }
//
//        classificacaos = separarESalvarClassificacao(jogos, classificacaos);
//
//        Double mediaAmbas = Double.valueOf(0);
//        for (Classificacao classificacao : classificacaos) {
//            mediaAmbas += classificacao.getAmbasMarcam();
//        }
//
//        mediaAmbas = (mediaAmbas/classificacaos.size());
//
//        mediaAmbas = mediaAmbas + mediaAmbas/4;
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("TIMES ACIMA DA MEDIA DE AMBAS MARCAM");
//        sb.append("\n");
//        for (Classificacao classificacao : classificacaos) {
//            if(classificacao.getQuantidadeOver25() > mediaAmbas){
//                sb.append(classificacao.getTime() + " - " + classificacao.getQuantidadeOver25());
//                sb.append("\n");
//            }
//        }
//
//        return sb;
//    }

}
