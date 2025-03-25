package com.example.letstrip.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.entity.ChatMessage;
import com.example.letstrip.repository.ChatMemberRepository;
import com.example.letstrip.repository.ChatMessageRepository;

@Repository
public class ChatMessageDAO {
	
	@Autowired
	ChatMessageRepository chatMessageRepository;
	
	// 채팅 내역 저장 
	public boolean saveChatMessage(ChatMessage chatMessage) {
		boolean result = false;
		ChatMessage chatMessageSave = chatMessageRepository.save(chatMessage);
		if(chatMessageSave != null) {
			result = true;
		}
		return result;
	}

	// 채팅 내역 찾기 
	public String chatcontent(String chatroomId, String personId) {
		return chatMessageRepository.findByRoomIdAndPersonId(chatroomId, personId);
	}

}
