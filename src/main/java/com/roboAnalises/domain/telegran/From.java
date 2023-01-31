package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class From {
    public String language_code;
    public Long id;
    public boolean is_bot;
    public String first_name;
    public String username;
    public String last_name;
}
