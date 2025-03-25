package com.example.letstrip.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageId implements Serializable {

    private String chatroomid;  // 채팅방 고유 아이디
    private String id;          // 사용자 아이디

}
