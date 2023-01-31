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
public class VerificarEntradas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String liga;
    private String hora;
    private String minutos;
    private String aposta;
    private Integer idMessage;
    private Boolean flagFinalizado;
    private Boolean flagGrem;
    private String padrao;
    private Integer minuto1;
    private Integer minuto2;
    private Integer minuto3;

}
