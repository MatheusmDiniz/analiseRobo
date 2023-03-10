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

    private Long contEntradas = 0L;
    private Long contGrens = 0L;
    private Long contReds = 0L;
    private Double total = Double.valueOf(0);


    public void addEntradas(Long a){
        if(this.contEntradas < 1){
            this.contEntradas = a;
        }else{
            this.contEntradas = this.contEntradas + a;
        }
    }

    public void addGrens(Long a){
        if(this.contGrens < 1){
            this.contGrens = a;
        }else{
            this.contGrens = this.contGrens + a;
        }
    }

    public void addRed(Long a){
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
