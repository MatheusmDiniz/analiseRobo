package com.roboAnalises.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum LigasEnum {
    EURO("eurocup", 3, "EURO"),
    COPA("worldcup", 2, "COPA"),
    PREMIER("premiership", 0, "PREMIER"),
    SULAMERICA("sulamericana", 4, "SULAMERICA");

    public static LigasEnum find(int valor){
        for(LigasEnum v : values()){
            if(v.value == valor){
                return v;
            }
        }
        return null;
    }

    public static LigasEnum find(String valorString){
        int valor = Integer.parseInt(valorString);
        for(LigasEnum v : values()){
            if(v.value == valor){
                return v;
            }
        }
        return null;
    }

    private Integer value;
    private String nomeOficial;
    private String nome;

    LigasEnum(String nomeOficial, Integer value, String nome) {
        this.nomeOficial = nomeOficial;
        this.value = value;
        this.nome = nome;
    }

    public static LigasEnum getLiga(String liga) {
        if(liga.equals(LigasEnum.EURO.getNome())){
            return LigasEnum.EURO;
        }

        if(liga.equals(LigasEnum.COPA.getNome())){
            return LigasEnum.COPA;
        }

        if(liga.equals(LigasEnum.PREMIER.getNome())){
            return LigasEnum.PREMIER;
        }

        if(liga.equals(LigasEnum.SULAMERICA.getNome())){
            return LigasEnum.SULAMERICA;
        }

        return null;
    }

    public static List<String> getLigas(){
        List<String> ligas = new ArrayList<>();

        ligas.add(LigasEnum.EURO.getNome());
        ligas.add(LigasEnum.COPA.getNome());
        ligas.add(LigasEnum.PREMIER.getNome());
        ligas.add(LigasEnum.SULAMERICA.getNome());

        return ligas;

    }


    public String getNomeOficial() {
        return nomeOficial;
    }

    public String getNome(){
        return nome;
    }

    public Integer getValue(){
        return value;
    }
}
