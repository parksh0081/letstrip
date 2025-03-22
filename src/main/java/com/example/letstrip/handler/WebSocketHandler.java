package com.example.letstrip.handler;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    // 클라이언트로부터 메시지를 받았을 때 처리하는 메서드
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("Received message: " + message.getPayload());
        
        // 클라이언트로 응답 메시지 보내기
        String responseMessage = "Hello, you sent: " + message.getPayload();
        session.sendMessage(new TextMessage(responseMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        System.out.println("Connection closed: " + session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New connection established: " + session.getId());
    }
}
