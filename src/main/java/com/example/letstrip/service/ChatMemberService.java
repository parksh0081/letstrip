package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.ChatMemberDAO;

@Service
public class ChatMemberService {
	
	@Autowired
	ChatMemberDAO dao;

	// 채팅방 멤버 저장 혹은 갱신 
	public void saveChatMember(String personId, String roomCode) {
		dao.saveChatMember(personId, roomCode);
	}

}
