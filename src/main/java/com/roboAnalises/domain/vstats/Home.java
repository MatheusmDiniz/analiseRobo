package com.roboAnalises.domain.vstats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Home {
    public String id;
    public String name;

    public Home(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
