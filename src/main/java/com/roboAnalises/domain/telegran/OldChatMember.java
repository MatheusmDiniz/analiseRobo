package com.roboAnalises.domain.telegran;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OldChatMember {
    public User user;
    public String status;
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
    public boolean can_post_messages;
}
