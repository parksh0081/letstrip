package com.example.letstrip.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chatmember")
public class ChatMember {

    @EmbeddedId
    private ChatMemberId id; // 복합 기본 키 (chatroomid, id)

    @ManyToOne
    @MapsId("chatroomid") // 복합 키 중 chatroomid와 연결
    @JoinColumn(name = "chatroomid", insertable = false, updatable = false)
    private ChatRoom chatroom; // ChatRoom 엔티티와 관계 (채팅방 엔티티)

    @ManyToOne
    @MapsId("id") // 복합 키 중 id와 연결
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Person person; // Person 엔티티와 관계 (사용자 엔티티)

}


