package com.example.letstrip.dto;

import com.example.letstrip.entity.ChatMember;
import com.example.letstrip.entity.ChatMemberId;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.entity.Person;

import lombok.Data;

@Data
public class ChatMemberDTO {
    private String chatroomid;
    private String id;
    
    public ChatMember toEntity(ChatRoom chatroom, Person person) {
        // 복합 키 생성
        ChatMemberId chatMemberId = new ChatMemberId(chatroom.getChatroomid(), person.getId());
        
        // ChatMember 엔티티 생성
        return new ChatMember(chatMemberId, chatroom, person);
    }
}
