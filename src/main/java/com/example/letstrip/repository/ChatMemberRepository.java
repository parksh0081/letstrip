package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.ChatMember;
import com.example.letstrip.entity.ChatMemberId;

public interface ChatMemberRepository extends JpaRepository<ChatMember, ChatMemberId>{

}
