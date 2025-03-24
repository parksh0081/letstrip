package com.example.letstrip.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATMESSAGE_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "CHATMESSAGE_SEQUENCE_GENERATOR", sequenceName = "seq_chatmessage", initialValue = 1, allocationSize = 1)
    private int messageid;  // 채팅 메시지 고유 아이디

    @ManyToOne
    @JoinColumn(name = "chatroomid", insertable = false, updatable = false)
    private ChatRoom chatroom;  // ChatRoom 엔티티와 관계 (채팅방 엔티티)

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Person person;  // Person 엔티티와 관계 (사용자 엔티티)

    private Date sendtime; 
    private String chatcontent; 
}
