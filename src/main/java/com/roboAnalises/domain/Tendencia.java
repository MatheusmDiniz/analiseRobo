package com.roboAnalises.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Tendencia {
    private Long hora;
    private Long gols;
    private Double mediaGols;
}
