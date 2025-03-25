package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>{

	// 채팅방 이름
	@Query(value = "select chatroomname from chatroom where chatroomid = :chatroomid", nativeQuery = true)
	String findByChatRoomId(@Param("chatroomid")String chatroomid);	
}