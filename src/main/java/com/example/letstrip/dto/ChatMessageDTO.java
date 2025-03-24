package com.example.letstrip.dto;

import java.util.Date;

import com.example.letstrip.entity.ChatMessage;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.entity.Person;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private int messageid;
    private String chatroomid;
    private String id;
    private Date sendtime;
    private String chatcontent;
    
    public ChatMessage toEntity(ChatRoom chatroom, Person person) {
    	return new ChatMessage(messageid, chatroom, person, sendtime, chatcontent);
    }
}
