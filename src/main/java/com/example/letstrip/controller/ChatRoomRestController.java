package com.example.letstrip.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.letstrip.dto.ChatMessageDTO;
import com.example.letstrip.dto.ChatRoomDTO;
import com.example.letstrip.entity.ChatMessage;
import com.example.letstrip.entity.ChatMessageId;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.entity.Person;
import com.example.letstrip.repository.ChatRoomRepository;
import com.example.letstrip.repository.PersonRepository;
import com.example.letstrip.service.ChatMessageService;
import com.example.letstrip.service.ChatRoomService;

import jakarta.servlet.http.HttpSession;

@RestController
public class ChatRoomRestController {
    
    @Autowired
    ChatRoomService chatroomService;
    
    @Autowired
    ChatRoomRepository chatRoomRepository;
    
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    ChatMessageService chatMessageService;
    
	// 채팅방 저장
	@PostMapping("/createChatRoom")
	public ResponseEntity<Map<String, Object>> createChatRoom(@RequestBody ChatRoomDTO dto) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        // ChatRoomDTO를 Service로 전달
	    	ChatRoom chatRoom = chatroomService.chatRoomWrite(dto);
	        if (chatRoom != null) {
	            response.put("success", true);
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("success", false);
	            return ResponseEntity.status(500).body(response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false);
	        return ResponseEntity.status(500).body(response);
	    }
	}    
    
    
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
    
    // 채팅 내역 가져오기
    @GetMapping("/mateboard/chatRoomContent")
    public ResponseEntity<Map<String, String>> chatRoomContent(@RequestParam("chatroomId") String chatroomId, HttpSession session) {
    	// chatroomId에 해당하는 채팅방 내용을 가져오는 서비스 메서드 호출
    	String personId = (String) session.getAttribute("personId");
        String chatContent = chatMessageService.chatcontent(chatroomId, personId);

        // 응답으로 JSON 형태로 채팅방 내용을 반환
        Map<String, String> response = new HashMap<>();
        response.put("chatContent", chatContent);
        return ResponseEntity.ok(response);
    }    
}

