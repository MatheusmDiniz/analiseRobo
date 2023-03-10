package com.roboAnalises.service;

import com.roboAnalises.domain.Entradas;
import com.roboAnalises.domain.Estatisticas;
import com.roboAnalises.domain.VerificarEntradas;
import com.roboAnalises.domain.enums.Apostas;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.repository.EntradasRepository;
import com.roboAnalises.util.Data;
import com.roboAnalises.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EntradasService {

    EntradasRepository entradasRepository;

    @Autowired
    public EntradasService(EntradasRepository entradasRepository){
        this.entradasRepository = entradasRepository;
    }

    public void salvarEntrada(Entradas entrada){
        entradasRepository.save(entrada);
    }

    public boolean verificarEntradaDuplicada(Entradas entrada) {
        Entradas e = entradasRepository.findByLigaAndHoraAndMinutosAndApostaAndData(entrada.getLiga(), entrada.getHora(), entrada.getMinutos(), entrada.getAposta(), entrada.getData());

        if(Objects.nonNull(e)){
            return false;
        }

        return true;
    }

    public List<Entradas> pegarEntradasNaoFinalizadas(){
        return entradasRepository.findEntradasAndNotFinalizadas();
    }

    public void limparBanco() {
        entradasRepository.deleteAll();
    }

    public Long findEntradasNaoFinalizadas() {
        return entradasRepository.countFindEntradasNaoFinalizadas();
    }

    public void verificarEntradasNaoFinalizadasFinalizadas(){
        List<Entradas> entradasNaoFinalizadas = entradasRepository.findEntradasNaoFinalizadas();

        ApiVsStatsService pd = new ApiVsStatsService();
        List<Jogos> ultimosJogos = pd.getUltimosJogosTodasLigas();
        logicaParaFazerUpdateDaEntrada(entradasNaoFinalizadas,ultimosJogos);

    }

    public void logicaParaFazerUpdateDaEntrada(List<Entradas> entradas, List<Jogos> ultimosJogos) {

        for (Entradas entrada : entradas) {
            String[] strArr = entrada.getMinutos().split("-");
            List<Long> minutos = new ArrayList<>();
            for (String s : strArr) {
                minutos.add(Long.parseLong(s));
            }

            if(!Data.verificarUltimoMinutoJogoMaiorHoraLondresRetornaTrue(Integer.valueOf(entrada.getHora()), Integer.valueOf(String.valueOf(minutos.get(2))), entrada.getData())){
                continue;
            }

            entrada.setFlagFinalizado(true);

            Boolean isEntradaQuebrada = Util.isEntradaQuebrada(minutos.get(0), minutos.get(2));

            if(isEntradaQuebrada){
                for (Long minuto : minutos) {
                    Boolean isMinutoHoraAtualEntrada = Util.isMinutoHoraAtualEntrada(minuto);
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
                for (Long minuto : minutos) {
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
                salvarEntrada(entrada);
            }
        }
    }

    private static void logicaTipoAposta(Entradas entrada, Jogos jogo, Long minuto, String hora) {
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
