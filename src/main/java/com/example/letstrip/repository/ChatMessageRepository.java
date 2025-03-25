package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.ChatMessage;
import com.example.letstrip.entity.ChatMessageId;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, ChatMessageId>{

	// 세션 아이디와 채팅방 코드로 내역 찾기
	@Query(value = "select chatcontent from chatmessage where chatroomid=:chatroomId and id=:personId", nativeQuery = true)
	String findByRoomIdAndPersonId(@Param("chatroomId")String chatroomId, @Param("personId")String personId);
	
}
