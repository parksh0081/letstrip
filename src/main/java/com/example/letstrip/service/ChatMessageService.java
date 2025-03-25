package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.ChatMessageDAO;
import com.example.letstrip.entity.ChatMessage;

@Service
public class ChatMessageService {
	
	@Autowired
	ChatMessageDAO dao;

	// 채팅 내역 저장 
	public boolean saveChatMessage(ChatMessage chatMessage) {
		return dao.saveChatMessage(chatMessage);
	}

	// 채팅 내역 가져오기 
	public String chatcontent(String chatroomId, String personId) {
		return dao.chatcontent(chatroomId, personId);
	}
}
