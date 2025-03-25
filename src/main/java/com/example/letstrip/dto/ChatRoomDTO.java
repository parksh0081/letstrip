package com.example.letstrip.dto;

import com.example.letstrip.entity.ChatRoom;

import lombok.Data;

@Data
public class ChatRoomDTO {
    private String chatroomid;
    private String chatroomname;
    private java.util.Date chattime;
    
    public ChatRoom toEntity() {
    	return new ChatRoom(chatroomid, chatroomname, chattime);
    }
}
