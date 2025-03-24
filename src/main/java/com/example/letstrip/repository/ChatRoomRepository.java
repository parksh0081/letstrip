package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>{

}
