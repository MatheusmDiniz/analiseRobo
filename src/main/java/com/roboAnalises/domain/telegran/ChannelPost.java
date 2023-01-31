package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChannelPost {
    public Long date;
    public Chat chat;
    public SenderChat sender_chat;
    public Long message_id;
    public String text;
    public ForwardFrom forward_from;
    public int forward_date;
}
