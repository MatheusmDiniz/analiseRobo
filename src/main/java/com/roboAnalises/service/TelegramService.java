package com.roboAnalises.service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.roboAnalises.domain.Entradas;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Token;
import com.roboAnalises.util.Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Service
public class TelegramService {

    @Autowired
    EntradasService entradasService;

    @Autowired
    ClassificacaoService classificacaoService;

    @Autowired
    InformacoesService informacoesService;

    private final String endpoint = "https://api.telegram.org/";
    private final String token = Token.TOKEN_ROBO;
    private final String chatId = Token.CHAT_ID_GRUPO;


    public JSONObject sendMessage(String text) throws UnirestException {
        return Unirest.post(endpoint + "bot" + token + "/sendMessage")
                .field("chat_id", chatId)
                .field("text", text)
                .asJson()
                .getBody()
                .getObject()
                .getJSONObject("result");
    }

    public void montarMensagem(Jogos jogo, String aposta, String tipoEntrada) {

        LocalTime lt = LocalTime.of(jogo.getHour(), jogo.getMinute(),0);


        int m1 = 0;
        int m2 = 0;
        int m3 = 0;

        if(tipoEntrada.equals(TipoEntrada.QUINA)){
            lt = lt.plusHours(1);
            m1 = lt.plusMinutes(3).getMinute();
            m2 = lt.plusMinutes(6).getMinute();
            m3 = lt.plusMinutes(9).getMinute();
        }else if(tipoEntrada.equals(TipoEntrada.CIMA_MAIS_UM)){
            lt = lt.plusHours(1);
            m1 = lt.getMinute();
            m2 = lt.plusMinutes(3).getMinute();
            m3 = lt.plusMinutes(6).getMinute();
        }else{
            m1 = lt.plusMinutes(15).getMinute();
            m2 = lt.plusMinutes(18).getMinute();
            m3 = lt.plusMinutes(21).getMinute();
        }

        lt = lt.minusMinutes(3);

        Integer hour = lt.getHour();

        String liga = jogo.getLiga()  ;
        String hora = String.valueOf(hour);
        String minutos = m1 + "-" + m2 + "-" + m3;

//        if(m3 >= 0 && m3 <= 8){
//            return;
//        }

        StringBuilder mensagemTelegram = Util.montarMensagemTelegram(liga, minutos, aposta);
        mensagemTelegram.append("\n-----------------------\n");
        mensagemTelegram.append(classificacaoService.getTimesOver25AndAmbasMarcam(LigasEnum.getLiga(liga)));

        List<Jogos> jogos = new ApiVsStatsService(LigasEnum.getLiga(liga).getNomeOficial()).getLast200Jogos();
        mensagemTelegram.append(informacoesService.getMediaOverUltimas8Linhas(LigasEnum.getLiga(liga), jogos));
        mensagemTelegram.append(informacoesService.getMediaUltimasLinhasEPorQuadrante(LigasEnum.getLiga(liga), jogos));
        mensagemTelegram.append(informacoesService.getMelhoresLigas());

        Entradas entrada = new Entradas();
        entrada.setLiga(liga);
        entrada.setHora(hora);
        entrada.setMinutos(minutos);
        entrada.setAposta(aposta);
        entrada.setData(LocalDate.now(ZoneId.of("America/Sao_Paulo")).toString());

        if(entradasService.verificarEntradaDuplicada(entrada)){
            try {
                JSONObject retornoEnvioMensagem =  sendMessage(mensagemTelegram.toString());

                entrada.setIdMessage((Integer) retornoEnvioMensagem.get("message_id"));
                entrada.setFlagFinalizado(false);
                entrada.setFlagGrem(false);
                entradasService.salvarEntrada(entrada);

            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("ENTRADA DUPLICADA");
        }
    }

}
