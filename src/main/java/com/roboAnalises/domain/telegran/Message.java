package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Message {
    public Long date;
    public Chat chat;
    public Long message_id;
    public From from;
    public String text;
    public NewChatParticipant new_chat_participant;
    public List<NewChatMember> new_chat_members;
    public NewChatMember new_chat_member;
    public Document document;
    public Animation animation;
    public ReplyToMessage reply_to_message;
    public List<Entity> entities;
}
