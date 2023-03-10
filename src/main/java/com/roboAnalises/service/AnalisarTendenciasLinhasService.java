package com.roboAnalises.service;

import com.roboAnalises.domain.Tendencia;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Data;

import java.util.ArrayList;
import java.util.List;

public class AnalisarTendenciasLinhasService {


    public void analisarTendenciaLinhas(){
        ApiVsStatsService apiVsStatsService = new ApiVsStatsService("worldcup");

        List<Jogos> last200Jogos = apiVsStatsService.getLast200Jogos();

        List<Integer> horas = Data.getUltimas8HorasLondres();
        List<Tendencia> tendencias = new ArrayList<>();

        for (Integer hora : horas) {
            Tendencia t = new Tendencia();
            t.setHora(Long.valueOf(hora));
            t.setGols(0L);
            for (Jogos lastGame : last200Jogos) {
                if(lastGame.getHour() == hora){
                    t.setGols(t.getGols() + lastGame.getScoreFullTime().away + lastGame.getScoreFullTime().home);
                }
            }
            tendencias.add(t);
        }

        Long totalGols = 0L;

        for (Tendencia tendencia : tendencias) {
            totalGols += tendencia.getGols();
        }

        Double mediaGolsPorLinha = Double.valueOf(totalGols/tendencias.size());

        Double mediaQuadrante = Double.valueOf(mediaGolsPorLinha/4);

    }
}
