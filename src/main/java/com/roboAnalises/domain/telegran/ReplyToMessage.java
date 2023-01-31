package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReplyToMessage {
    public int date;
    public Chat chat;
    public int message_id;
    public From from;
    public String text;
}
