package com.roboAnalises.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MediaMinutosHoras {
    private int hora;
    private int m1;
    private int m2;
    private int m3;
}
