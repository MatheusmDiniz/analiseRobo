package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Result {
    public Long update_id;
    public MyChatMember my_chat_member;
    public ChannelPost channel_post;
    public Message message;
}
