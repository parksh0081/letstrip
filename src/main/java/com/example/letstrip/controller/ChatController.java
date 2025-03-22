//package com.example.letstrip.controller;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class ChatController {
//	
//    @GetMapping("/chater")
//    public String chaterPage() {
//        return "/mateboard/chater"; // resources 폴더에 있는 chater.html을 반환
//    }
//
//    @MessageMapping("/hello") // "/app/hello"로 매핑
//    @SendTo("/topic/greetings") // "/topic/greetings"로 메시지를 보냄
//    public String greeting(String message) {
//        return "Hello, you sent: " + message;
//    }
//
//}

