package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewChatMember {
    public boolean can_delete_messages;
    public boolean can_edit_messages;
    public boolean can_promote_members;
    public boolean can_invite_users;
    public boolean can_manage_chat;
    public boolean can_be_edited;
    public boolean can_manage_voice_chats;
    public boolean can_restrict_members;
    public boolean can_change_info;
    public boolean is_anonymous;
    public boolean can_pin_messages;
    public User user;
    public boolean can_post_messages;
    public String status;
    public int id;
    public boolean is_bot;
    public String first_name;
    public String username;
}
