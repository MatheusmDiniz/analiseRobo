package com.roboAnalises.service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.roboAnalises.domain.Entradas;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.enums.TipoEntrada;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Data;
import com.roboAnalises.util.Token;
import com.roboAnalises.util.Util;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
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
            lt = lt.plusMinutes(21);
        }

        if(lt.getHour() != Data.getHoraAtualLondon()){
            return;
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

        List<Jogos> ultimos200Jogos = new ApiVsStatsService(LigasEnum.getLiga(liga).getNomeOficial()).getLast200Jogos();

//        if(RestricoesEntradasService.returnTrueIfVerificarMais3RedsSeguidosUltimaHora(ultimos200Jogos, aposta)){
//            return;
//        }

        List<Jogos> jogos = new ApiVsStatsService().getUltimosJogosTodasLigas();
        Double mediaOver25 = informacoesService.getMediaOverUltimas3Horas(LigasEnum.getLiga(liga), jogos);
        Double mediaAmbas = informacoesService.getMediaAmbasUltimas3Horas(LigasEnum.getLiga(liga), jogos);
        mensagemTelegram.append("\nMedia Over 2.5 nas ultimas 3 horas: " + mediaOver25+"\n");
        mensagemTelegram.append("\nMedia Ambas nas ultimas 3 horas: " + mediaAmbas+"\n");

        if(RestricoesEntradasService.returnTrueIfVerificarHoraComMaisOverOuAmbasQueAMedia(ultimos200Jogos, aposta, mediaOver25, mediaAmbas)){
            return;
        }

        if(RestricoesEntradasService.returnTrueIfAMediaDosMinutosDaEntradaForRuim(jogos,liga, aposta, String.valueOf(m1))){
            return;
        }

        mensagemTelegram.append(informacoesService.getMediaUltimasLinhasEPorQuadrante(LigasEnum.getLiga(liga), ultimos200Jogos));
        mensagemTelegram.append(informacoesService.getMelhoresLigasOver25Ultimas10Horas(jogos));
        mensagemTelegram.append(informacoesService.getMelhoresLigasAmbasUltimas10Horas(jogos));

        Entradas entrada = new Entradas();
        entrada.setLiga(liga);
        entrada.setHora(hora);
        entrada.setMinutos(minutos);
        entrada.setAposta(aposta);
        entrada.setData(LocalDate.now(ZoneId.of("Europe/London")).toString());

        if(entradasService.verificarEntradaDuplicada(entrada)){
            try {
                JSONObject retornoEnvioMensagem =  sendMessage(mensagemTelegram.toString());

                entrada.setIdMessage(Long.valueOf((Integer) retornoEnvioMensagem.get("message_id")));
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
