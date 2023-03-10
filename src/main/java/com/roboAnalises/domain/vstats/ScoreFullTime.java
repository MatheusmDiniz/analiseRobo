package com.roboAnalises.domain.vstats;

import com.roboAnalises.domain.Score;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ScoreFullTime extends Score {
    public int home;
    public int away;

    public ScoreFullTime(int i, int i1) {
        this.home = i;
        this.away = i1;
    }
}
