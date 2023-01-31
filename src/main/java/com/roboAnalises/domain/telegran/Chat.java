package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Chat {
    public Long id;
    public String title;
    public String type;
    public String first_name;
    public String username;
    public boolean all_members_are_administrators;
    public String last_name;
}
