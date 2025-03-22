package com.example.letstrip.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지를 받아서 모든 사용자에게 전달
        for (WebSocketSession s : sessions.values()) {
            s.sendMessage(message); // 메시지를 보냄
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomCode = (String) session.getAttributes().get("roomCode");
        sessions.put(roomCode, session); // 방 코드별로 연결된 세션 저장
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 닫힘 처리
        sessions.values().remove(session);
    }
}
