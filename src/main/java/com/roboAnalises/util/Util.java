package com.roboAnalises.util;



import com.roboAnalises.domain.AnalisePadroes;
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

    public static Boolean isEntradaQuebrada(Long jogo1, Long jogo4){
        if(jogo4 - jogo1 < 0){
            return true;
        }else {
            return false;
        }
    }

    public static Boolean isMinutoHoraAtualEntrada(Long minuto){
        Long[] ultimosMinutosHora = {51L, 52L, 53L, 54L, 55L, 56L, 57L, 58L, 59L};

        for(int i = 0; i < ultimosMinutosHora.length; i++){
            if(minuto == ultimosMinutosHora[i]){
                return true;
            }
        }
        return false;
    }

    public static Boolean verificarPorcentagemPadrao(Double total, Long contReds, Long contEntradas, Long contGrens) {
//        Long quantidadeReds = (contReds*7)+5;

        if(!(total > 94)){
            return false;
        }
//
//        if((contGrens > quantidadeReds) && contEntradas > 10){
//            return true;
//        }

        if(contEntradas > 7 && contReds<2){
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

        if(estatisticas.getTotal().isNaN()){
            e.setTotal(0.0);
        }

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

    public static boolean isPadraoQuinto(AnalisePadroes analise) {
        int indexInicial = analise.getPadrao().length() - 6;

        if(analise.getPadrao().substring(indexInicial).toLowerCase().equals("quinto")){
            return true;
        }
        return false;

    }
}
