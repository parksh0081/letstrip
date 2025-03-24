package com.example.letstrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatRoomController {

    @GetMapping("/chatroom")
    public String chatRoom(@RequestParam("roomCode") String roomCode, Model model) {
        model.addAttribute("roomCode", roomCode);
        return "/mateboard/chatroom"; // 채팅방 화면 템플릿
    }
}
