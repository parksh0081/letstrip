package com.example.letstrip.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.letstrip.service.ChatMemberService;
import com.example.letstrip.service.ChatMessageService;
import com.example.letstrip.service.ChatRoomService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatRoomController {
	
	@Autowired
	ChatMemberService chatMemberService;
	
	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
	ChatMessageService chatMessageService;
	
	// 채팅방 생성 
    @GetMapping("/chatroom")
    public String chatRoom(@RequestParam("roomCode") String roomCode, Model model) {
        model.addAttribute("roomCode", roomCode);
        return "/mateboard/chatroom"; 
    }
    
    // 채팅방 리스트 생성 
    @GetMapping("/mateboard/chatList")
    public String chatList(HttpSession session, Model model) {
        String personId = (String) session.getAttribute("personId");

        List<String> list = chatMemberService.chatList(personId);

        // 채팅방 ID와 이름을 담을 리스트
        List<String[]> chatRoomIdAndName = new ArrayList<>();

        for (String chatroomid : list) {
            String chatroomName = chatRoomService.chatroomName(chatroomid);
            chatRoomIdAndName.add(new String[]{chatroomid, chatroomName});
        }
 
        model.addAttribute("personId", personId);
        model.addAttribute("chatRoomIdAndName", chatRoomIdAndName); 

        return "/mateboard/chatList";
    }
   
}
