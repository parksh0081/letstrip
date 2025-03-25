package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.ChatRoomDAO;
import com.example.letstrip.dto.ChatRoomDTO;
import com.example.letstrip.entity.ChatRoom;

@Service
public class ChatRoomService {
	
	@Autowired
	ChatRoomDAO dao;
	
	// 채팅방 입장 시 채팅방 저장
	public ChatRoom chatRoomWrite(ChatRoomDTO dto) {
		return dao.chatRoomWrite(dto);
	}
}
