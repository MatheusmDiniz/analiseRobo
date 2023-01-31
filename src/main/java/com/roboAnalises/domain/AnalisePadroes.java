package com.roboAnalises.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AnalisePadroes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String padrao;
    private String liga;
    private Boolean isPorcentagemBoa;

    @Embedded
    private Estatisticas estatisticas;
}
