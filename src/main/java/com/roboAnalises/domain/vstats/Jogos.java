package com.roboAnalises.domain.vstats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Jogos {
        public long id;
        public String date;
        public String time;
        public int hour;
        public int minute;
        public Home home;
        public Away away;
        public ScoreFullTime scoreFullTime;
        public ScoreHalfTime scoreHalfTime;
        public String odds;
        public String Liga;


        public String getIdString(){
                return ""+this.id;
        }
}
