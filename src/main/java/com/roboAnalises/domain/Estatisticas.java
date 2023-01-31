package com.roboAnalises.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Estatisticas {

    private Integer contEntradas = 0;
    private Integer contGrens = 0;
    private Integer contReds = 0;
    private Double total = Double.valueOf(0);


    public void addEntradas(int a){
        if(this.contEntradas < 1){
            this.contEntradas = a;
        }else{
            this.contEntradas = this.contEntradas + a;
        }
    }

    public void addGrens(int a){
        if(this.contGrens < 1){
            this.contGrens = a;
        }else{
            this.contGrens = this.contGrens + a;
        }
    }

    public void addRed(int a){
        if(this.contReds < 1){
            this.contReds = a;
        }else{
            this.contReds = this.contReds + a;
        }
    }

    public Double getTotal(){
        Double grem = Double.valueOf(this.getContGrens());
        Double entradas = Double.valueOf(this.getContEntradas());

        return (grem/entradas)*100;

    }
}
