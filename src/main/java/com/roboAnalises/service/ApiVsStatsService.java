package com.roboAnalises.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.roboAnalises.domain.enums.LigasEnum;
import com.roboAnalises.domain.vstats.Jogos;
import com.roboAnalises.util.Token;
import com.roboAnalises.util.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiVsStatsService {
    String url = "https://xss5jaebg4.execute-api.sa-east-1.amazonaws.com/prod/";
    String ultimosJogos = null;
    String proximosJogos = null;
    String informacoesProximosJogos = null;

    public ApiVsStatsService(String liga){
        this.ultimosJogos = "lastMatches/"+liga;
        this.proximosJogos = "futureMatches/"+liga;
        this.informacoesProximosJogos = "matchAnalysis/"+liga;
    }

    public ApiVsStatsService(){

    }

    public List<Jogos> getLastGames() {
        List<Jogos> jogos = new ArrayList<>();
        try {

            URL url = new URL(this.url + this.ultimosJogos);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", Token.TOKEN_AUTHORIZATION);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String jsonEmString = Util.converteJsonEmString(br);
            conn.disconnect();

            Gson gson = new Gson();

            ObjectMapper mapper = new ObjectMapper();
            jogos = mapper.readValue(jsonEmString, new TypeReference<List<Jogos>>(){});


        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return colocarNomeLiga(jogos);
    }

    private List<Jogos> colocarNomeLiga(List<Jogos> jogos) {
        for (Jogos jogo : jogos) {
            if(jogo.getIdString().substring(jogo.getIdString().length()-1).equals(LigasEnum.PREMIER.getValue().toString())){
                jogo.setLiga(LigasEnum.PREMIER.getNome());
            }

            if(jogo.getIdString().substring(jogo.getIdString().length()-1).equals(LigasEnum.COPA.getValue().toString())){
                jogo.setLiga(LigasEnum.COPA.getNome());
            }

            if(jogo.getIdString().substring(jogo.getIdString().length()-1).equals(LigasEnum.EURO.getValue().toString())){
                jogo.setLiga(LigasEnum.EURO.getNome());
            }

            if(jogo.getIdString().substring(jogo.getIdString().length()-1).equals(LigasEnum.SULAMERICA.getValue().toString())){
                jogo.setLiga(LigasEnum.SULAMERICA.getNome());
            }
        }

        return jogos;
    }

    public List<Jogos> getLast200Jogos(){
        List<Jogos> lastGames = getLastGames();

        List<Jogos> ultimos200Jogos = new ArrayList<>();

        for (int i = 0; i<200; i++){
            ultimos200Jogos.add(lastGames.get(i));
        }

        return ultimos200Jogos;
    }

    public List<Jogos> getUltimosJogosTodasLigas(){
       List<Jogos> jogos = new ArrayList<>();

        this.ultimosJogos = "lastMatches/"+LigasEnum.COPA.getNomeOficial();
        jogos.addAll(getLastGames());

        this.ultimosJogos = "lastMatches/"+LigasEnum.EURO.getNomeOficial();
        jogos.addAll(getLastGames());

        this.ultimosJogos = "lastMatches/"+LigasEnum.PREMIER.getNomeOficial();
        jogos.addAll(getLastGames());

        this.ultimosJogos = "lastMatches/"+LigasEnum.SULAMERICA.getNomeOficial();
        jogos.addAll(getLastGames());

        return jogos;
    }
}
