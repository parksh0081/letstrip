package com.example.letstrip.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.entity.ChatMember;
import com.example.letstrip.entity.ChatMemberId;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.entity.Person;
import com.example.letstrip.repository.ChatMemberRepository;
import com.example.letstrip.repository.ChatRoomRepository;
import com.example.letstrip.repository.PersonRepository;

@Repository
public class ChatMemberDAO {

    @Autowired
    private ChatMemberRepository chatMemberRepository;
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public void saveChatMember(String personId, String roomCode) {
        // personId가 null인 경우 예외를 던짐
        if (personId == null) {
            throw new IllegalArgumentException("error");
        }

        // 기존에 참가한 기록이 있는지 확인
        ChatMember existingMember = chatMemberRepository.findByChatroomidAndId(roomCode, personId);

        if (existingMember == null) {  // 새로운 사람 
            ChatMember chatMember = new ChatMember();
            ChatMemberId memberId = new ChatMemberId();
            memberId.setChatroomid(roomCode);
            memberId.setId(personId); 
            chatMember.setId(memberId);

            // 채팅방 존재 여부 확인
            ChatRoom chatRoom = chatRoomRepository.findById(roomCode).orElse(null);
            
            if (chatRoom == null) {
                throw new IllegalArgumentException("ChatRoom with roomCode " + roomCode + " does not exist.");
            }
            
            chatMember.setChatroom(chatRoom);  // 채팅방이 null이 아니면 설정

            // Person 엔티티를 personId로 찾음
            Person person = personRepository.findById(personId).orElse(null);
            
            if (person == null) {
                throw new IllegalArgumentException("Person with personId " + personId + " does not exist.");
            }

            chatMember.setPerson(person);  // person 설정

            // 참가자 저장
            chatMemberRepository.save(chatMember);
        }
    }

}
