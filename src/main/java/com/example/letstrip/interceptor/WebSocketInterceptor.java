package com.example.letstrip.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                    WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // ServerHttpRequest에서 세션 정보 가져오기
        String personId = (String) request.getHeaders().getFirst("personId");

        // WebSocket 세션의 attributes에 personId
        if (personId != null) {
            attributes.put("personId", personId);
        }
        
        // URL에서 roomName과 roomCode를 추출
        String roomName = request.getURI().getQuery();  // "roomName=〇〇&roomCode=Ton6rqkQyI"

        // URL 쿼리 파라미터에서 'roomName'과 'roomCode' 추출
        if (roomName != null) {
            String[] params = roomName.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    // 'roomName' 추출
                    if ("roomName".equals(keyValue[0])) {
                        // URL 디코딩 처리 (인코딩된 문자를 디코딩)
                        String decodedRoomName = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                        attributes.put("roomName", decodedRoomName);
                    }
                    // 'roomCode' 추출
                    if ("roomCode".equals(keyValue[0])) {
                        attributes.put("roomCode", keyValue[1]);
                    }
                }
            }
        }
       
        
        // WebSocket 연결 허용
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
