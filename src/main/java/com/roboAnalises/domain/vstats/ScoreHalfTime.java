package com.roboAnalises.domain.vstats;

import com.roboAnalises.domain.Score;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ScoreHalfTime extends Score {
    public int home;
    public int away;
}
