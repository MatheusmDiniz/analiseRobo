package com.roboAnalises.domain.vstats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Away {
    public String id;
    public String name;

    public Away(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
