package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.ChatMember;
import com.example.letstrip.entity.ChatMemberId;

public interface ChatMemberRepository extends JpaRepository<ChatMember, ChatMemberId>{
    
	@Query(value = "select * from chatmember where chatroomid=:chatroomid and id=:id", nativeQuery = true)
    ChatMember findByChatroomidAndId(@Param("chatroomid")String chatroomid, @Param("id")String id);
}
