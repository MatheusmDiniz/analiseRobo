package com.roboAnalises.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Tendencia {
    private Integer hora;
    private Integer gols;
    private Double mediaGols;
}
