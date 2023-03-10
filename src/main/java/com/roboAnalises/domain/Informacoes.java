package com.roboAnalises.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Informacoes {
    Long hora = 0L;
    Long quantidadeOver25 = 0L;

    String liga;

    public void addQuantidadeOver25(Long quantidadeOver25){
        this.quantidadeOver25 += quantidadeOver25;
    }

}
