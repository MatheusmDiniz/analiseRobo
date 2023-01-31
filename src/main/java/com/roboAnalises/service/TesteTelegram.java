package com.roboAnalises.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.roboAnalises.domain.telegran.Telegram;
import com.roboAnalises.util.Token;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class TesteTelegram {
    private final String endpoint = "https://api.telegram.org/";
    private final String token = Token.TOKEN_ROBO;

        private String senha = "1234";

    public TesteTelegram() {
    }

    public HttpResponse sendMessage(Long chatId, String text) throws UnirestException {
        return Unirest.post(endpoint + "bot" + token + "/sendMessage")
                .field("chat_id", chatId)
                .field("text", text)
                .field("Content-Type", "application/json")
                .asJson();
    }

    public HttpResponse getUpdates(Integer offset) throws UnirestException {
        return Unirest.post(endpoint + "bot" + token + "/getUpdates")
                .field("offset", offset)
                .asJson();
    }

    public HttpResponse updateMenssagem(Integer chatId, Integer idMensagem, String text) throws UnirestException {
        return Unirest.post(endpoint + "bot" + token + "/editMessageText")
                .field("chat_id", chatId)
                .field("text", text)
                .field("message_id", idMensagem)
                .asJson();
    }

    public HttpResponse deleteMensagem(Long chatId, Long idMensagem) throws UnirestException {
        return Unirest.post(endpoint + "bot" + token + "/deleteMessage")
                .field("chat_id", chatId)
                .field("message_id", idMensagem)
                .asJson();
    }

//    public void receberMensagens() throws UnirestException{
//        int last_update_id = 0; // controle das mensagens processadas
//        HttpResponse response;
//        while (true) {
//            response = getUpdates(last_update_id++);
//        }
//    }

    public void enviarMensagens() throws UnirestException{
//        sendMessage(Long.valueOf(ChatId.CANAL_STATUS_ROBO), "Subindo META TIPS "+ LocalTime.now(ZoneId.of("America/Sao_Paulo")));
//        sendMessage(Long.valueOf(ChatId.CHAT_MEU_ROBO_MEU), Util.mensagemEditadaComGrem(new Entradas("PREMIER", "00 03 06 09", Apostas.OVER25)).toString());
//        Util.mensagemEditadaComRed(new Entradas("PREMIER", "00 03 06 09", "AMBAS MARCAM")).toString()
    }

    public void receberMensagens() throws UnirestException{
        HttpResponse response;
        int cont = -1;
        while (cont < 6) {
            response = getUpdates(-1);
            if (response.getStatus() == 200) {

                Telegram root = new Telegram();

                ObjectMapper mapper = new ObjectMapper();
                try {
                    root = mapper.readValue(response.getBody().toString(), Telegram.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                root.getResult().forEach(result -> {
                    if(Objects.nonNull(result.getMessage()) && Objects.nonNull(result.getMessage().getText())){
                        if(result.getMessage().getText().equals("start "+this.senha)){
                            try {
                                deleteMensagem(Long.valueOf(result.getMessage().getChat().getId()), Long.valueOf(result.getMessage().getMessage_id()));
                                Random random = new Random();
                                int numero = random.nextInt(100)*4512;
                                this.senha = String.valueOf(numero);
                                sendMessage(Long.valueOf(result.getMessage().getChat().getId()), "SUBINDO ROBO META, NOVA SENHA:  "+ this.senha);
                            } catch (UnirestException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                System.out.println(root);

            }
            cont++;
        }
    }



}
