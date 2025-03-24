package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer>{

}
