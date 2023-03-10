package com.roboAnalises.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Entradas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String liga;
    private String hora;
    private String minutos;
    private String aposta;
    private Long idMessage;
    private Boolean flagFinalizado;
    private Boolean flagGrem;
    private String data;

    public Entradas(){}
    public Entradas(String premier, String s, String ambas_marcam) {
        this.liga = premier;
        this.minutos = s;
        this.aposta = ambas_marcam;
    }
}
