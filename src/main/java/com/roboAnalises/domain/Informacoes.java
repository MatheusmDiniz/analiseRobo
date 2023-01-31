package com.roboAnalises.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Informacoes {
    Integer hora = 0;
    Integer quantidadeOver25 = 0;

    String liga;

    public void addQuantidadeOver25(Integer quantidadeOver25){
        this.quantidadeOver25 += quantidadeOver25;
    }

}
