package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.ChatMessage;
import com.example.letstrip.entity.ChatMessageId;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, ChatMessageId>{

}
