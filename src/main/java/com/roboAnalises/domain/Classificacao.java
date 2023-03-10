package com.roboAnalises.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Classificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time")
    private String time;

    @Column(name = "gols_feitos")
    private Long golsFeitos = 0L;

    @Column(name = "gols_levados")
    private Long golsLevados = 0L;

    @Column(name = "quantidade_over25")
    private Long quantidadeOver25 = 0L;

    @Column(name = "quantidade_under25")
    private Long quantidadeUnder25 = 0L;

    @Column(name = "ambas_marcam")
    private Long ambasMarcam = 0L;

    @Column(name = "liga")
    private String liga;

    public void setGolsFeitos(Long golsFeitos) {
        this.golsFeitos += golsFeitos;
    }

    public void setGolsLevados(Long golsLevados) {
        this.golsLevados += golsLevados;
    }

    public void setQuantidadeOver25(Long quantidadeOver25) {
        this.quantidadeOver25 += quantidadeOver25;
    }

    public void setQuantidadeUnder25(Long quantidadeUnder25) {
        this.quantidadeUnder25 += quantidadeUnder25;
    }

    public void setAmbasMarcam(Long ambasMarcam) {
        this.ambasMarcam += ambasMarcam;
    }

    public void reseta(){
        this.golsFeitos =0L;
        this.golsLevados = 0L;
        this.quantidadeOver25 = 0L;
        this.quantidadeUnder25 = 0L;
        this.ambasMarcam = 0L;
    }
}
