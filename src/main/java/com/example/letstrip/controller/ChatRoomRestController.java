package com.example.letstrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.letstrip.dto.ChatMessageDTO;
import com.example.letstrip.entity.ChatMessage;
import com.example.letstrip.entity.ChatMessageId;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.entity.Person;
import com.example.letstrip.repository.ChatRoomRepository;
import com.example.letstrip.repository.PersonRepository;
import com.example.letstrip.service.ChatMessageService;

@RestController
public class ChatRoomRestController {
    
    @Autowired
    ChatRoomRepository chatRoomRepository;
    
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    ChatMessageService chatMessageService;
    
    // POST 방식으로 메시지 전송 시 처리하는 메서드
    @PostMapping("/chat/exit")
    public boolean exitMessages(@RequestBody ChatMessageDTO dto, @SessionAttribute(name = "personId", required = false) String personId) {
        // personId로 Person 객체를 찾기
        Person person = personRepository.findById(personId).orElse(null); // personId를 사용해 Person 찾기

        // roomCode로 ChatRoom 객체 찾기
        ChatRoom chatRoom = chatRoomRepository.findById(dto.getChatroomid()).orElse(null);

        // 새로운 메시지 엔티티 생성
        ChatMessage chatMessage = new ChatMessage();
        ChatMessageId messageId = new ChatMessageId(dto.getChatroomid(), personId);
        chatMessage.setId(messageId);  // 복합키 설정
        chatMessage.setChatroom(chatRoom);
        chatMessage.setPerson(person);
        chatMessage.setChatcontent(dto.getChatcontent());

        // 메시지 저장
        return chatMessageService.saveChatMessage(chatMessage);

    }
}
