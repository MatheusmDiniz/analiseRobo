package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MyChatMember {
    public Long date;
    public Chat chat;
    public OldChatMember old_chat_member;
    public From from;
    public NewChatMember new_chat_member;
}
