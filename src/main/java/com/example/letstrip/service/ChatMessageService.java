package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.ChatMessageDAO;
import com.example.letstrip.entity.ChatMessage;

@Service
public class ChatMessageService {
	
	@Autowired
	ChatMessageDAO dao;

	public boolean saveChatMessage(ChatMessage chatMessage) {
		return dao.saveChatMessage(chatMessage);
	}


}
