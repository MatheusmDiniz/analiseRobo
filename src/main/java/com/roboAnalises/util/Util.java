package com.roboAnalises.util;



import com.roboAnalises.domain.Estatisticas;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {
    public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }

    public static Boolean isEntradaQuebrada(Integer jogo1, Integer jogo4){
        if(jogo4 - jogo1 < 0){
            return true;
        }else {
            return false;
        }
    }

    public static Boolean isMinutoHoraAtualEntrada(Integer minuto){
        Integer[] ultimosMinutosHora = {51, 52, 53, 54, 55, 56, 57, 58, 59};

        for(int i = 0; i < ultimosMinutosHora.length; i++){
            if(minuto == ultimosMinutosHora[i]){
                return true;
            }
        }
        return false;
    }

    public static Boolean verificarPorcentagemPadrao(Double total, Integer contReds, Integer contEntradas, Integer contGrens) {
        Integer quantidadeReds = (contReds*7)+2;

        if(contGrens > quantidadeReds){
            return true;
        }

        return false;
    }
    public static Estatisticas estatisticaCompleto(Estatisticas estatisticas) {
        Estatisticas e = new Estatisticas();
        e.setContEntradas(estatisticas.getContEntradas());
        e.setContGrens(estatisticas.getContGrens());
        e.setContReds(estatisticas.getContReds());
        e.setTotal(estatisticas.getTotal());

        return e;

    }

    public static StringBuilder montarMensagemTelegram(String liga, String minutos, String aposta) {
        StringBuilder mensagemTelegram = new StringBuilder();
        mensagemTelegram.append("Liga: "+liga);
        mensagemTelegram.append("\n");
        mensagemTelegram.append("Minutos: "+minutos);
        mensagemTelegram.append("\n");
        mensagemTelegram.append("Aposta: "+aposta);
        mensagemTelegram.append("\n");
        return mensagemTelegram;
    }

}
