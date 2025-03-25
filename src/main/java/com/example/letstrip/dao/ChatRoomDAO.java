package com.example.letstrip.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.ChatRoomDTO;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.repository.ChatRoomRepository;

@Repository
public class ChatRoomDAO {
	
	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	// 채팅방 입장 시 채팅방 저장
	public ChatRoom chatRoomWrite(ChatRoomDTO dto) {
		dto.setChattime(new Date());
		ChatRoom chatRoom = dto.toEntity();
		return chatRoomRepository.save(chatRoom);
	}
}
