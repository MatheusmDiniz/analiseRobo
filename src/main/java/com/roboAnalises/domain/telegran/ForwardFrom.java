package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ForwardFrom {
    public int id;
    public boolean is_bot;
    public String first_name;
    public String username;
}
