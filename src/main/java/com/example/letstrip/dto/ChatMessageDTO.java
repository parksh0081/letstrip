package com.example.letstrip.dto;

import com.example.letstrip.entity.ChatMessage;
import com.example.letstrip.entity.ChatMessageId;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.entity.Person;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private String chatroomid;
    private String id;
    private String chatcontent;
    
    public ChatMessage toEntity(ChatRoom chatroom, Person person) {
        // 복합 키 생성
        ChatMessageId chatMessageId = new ChatMessageId(chatroom.getChatroomid(), person.getId());
        
        return new ChatMessage(chatMessageId, chatroom, person, chatcontent);
    }
}
