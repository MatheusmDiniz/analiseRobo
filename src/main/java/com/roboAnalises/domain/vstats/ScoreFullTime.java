package com.roboAnalises.domain.vstats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ScoreFullTime {
    public int home;
    public int away;

    public ScoreFullTime(int i, int i1) {
        this.home = i;
        this.away = i1;
    }
}
