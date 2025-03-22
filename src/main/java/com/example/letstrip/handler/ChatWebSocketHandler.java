//package com.example.letstrip.handler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//public class ChatWebSocketHandler extends TextWebSocketHandler {
//
//    private Map<String, WebSocketSession> sessions = new HashMap<>();
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // 메시지를 받아서 모든 사용자에게 전달
//        for (WebSocketSession s : sessions.values()) {
//            s.sendMessage(message); // 메시지를 보냄
//        }
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        String roomCode = (String) session.getAttributes().get("roomCode");
//        sessions.put(roomCode, session); // 방 코드별로 연결된 세션 저장
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        // 세션 닫힘 처리
//        sessions.values().remove(session);
//    }
//}

package com.example.letstrip.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    // 방 코드별로 세션 목록을 관리 (한 방에 여러 세션 가능)
    private Map<String, List<WebSocketSession>> sessions = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지를 받아서 해당 방에 모든 사용자에게 전달
        String roomCode = (String) session.getAttributes().get("roomCode");
        List<WebSocketSession> roomSessions = sessions.get(roomCode);
        
        if (roomSessions != null) {
            for (WebSocketSession s : roomSessions) {
                s.sendMessage(message); // 메시지를 보냄
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomCode = (String) session.getAttributes().get("roomCode");
        
        // 해당 방 코드에 대한 세션 목록을 가져오거나 새로 생성
        sessions.computeIfAbsent(roomCode, k -> new ArrayList<>()).add(session);
        
        // 입장 알림 메시지 보내기
        TextMessage enterMessage = new TextMessage("A participant has entered the chat.");
        
        // 해당 방에 연결된 모든 사용자에게 입장 메시지 전송
        for (WebSocketSession s : sessions.get(roomCode)) {
            if (s.isOpen()) {
                s.sendMessage(enterMessage);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomCode = (String) session.getAttributes().get("roomCode");
        
        // 해당 방 코드에서 세션 목록에서 제거
        List<WebSocketSession> roomSessions = sessions.get(roomCode);
        if (roomSessions != null) {
            roomSessions.remove(session);
        }

        // 퇴장 알림 메시지 보내기
        TextMessage leaveMessage = new TextMessage("A participant has left the chat.");
        
        // 해당 방에 연결된 모든 사용자에게 퇴장 메시지 전송
        if (roomSessions != null) {
            for (WebSocketSession s : roomSessions) {
                if (s.isOpen()) {
                    s.sendMessage(leaveMessage);
                }
            }
        }
    }
}
