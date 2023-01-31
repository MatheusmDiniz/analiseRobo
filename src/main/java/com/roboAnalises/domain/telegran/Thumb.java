package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Thumb {
    public String file_unique_id;
    public String file_id;
    public int width;
    public int file_size;
    public int height;
}
