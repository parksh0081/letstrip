package com.example.letstrip.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chatmessage")
public class ChatMessage {
	
    @EmbeddedId  // 복합 기본 키
    private ChatMessageId id;  // 복합 기본 키 객체

    @ManyToOne
    @JoinColumn(name = "chatroomid", insertable = false, updatable = false)
    private ChatRoom chatroom;  // ChatRoom 엔티티와 관계 (채팅방 엔티티)

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Person person;  // Person 엔티티와 관계 (사용자 엔티티)

    private String chatcontent; 
    
}
