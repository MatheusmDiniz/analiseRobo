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
    private Integer id;

    @Column(name = "time")
    private String time;

    @Column(name = "gols_feitos")
    private Integer golsFeitos = 0;

    @Column(name = "gols_levados")
    private Integer golsLevados = 0;

    @Column(name = "quantidade_over25")
    private Integer quantidadeOver25 = 0;

    @Column(name = "quantidade_under25")
    private Integer quantidadeUnder25 = 0;

    @Column(name = "ambas_marcam")
    private Integer ambasMarcam = 0;

    @Column(name = "liga")
    private String liga;

    public void setGolsFeitos(Integer golsFeitos) {
        this.golsFeitos += golsFeitos;
    }

    public void setGolsLevados(Integer golsLevados) {
        this.golsLevados += golsLevados;
    }

    public void setQuantidadeOver25(Integer quantidadeOver25) {
        this.quantidadeOver25 += quantidadeOver25;
    }

    public void setQuantidadeUnder25(Integer quantidadeUnder25) {
        this.quantidadeUnder25 += quantidadeUnder25;
    }

    public void setAmbasMarcam(Integer ambasMarcam) {
        this.ambasMarcam += ambasMarcam;
    }

    public void reseta(){
        this.golsFeitos =0;
        this.golsLevados = 0;
        this.quantidadeOver25 = 0;
        this.quantidadeUnder25 = 0;
        this.ambasMarcam = 0;
    }
}
