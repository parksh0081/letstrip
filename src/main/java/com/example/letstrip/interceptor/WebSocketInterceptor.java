package com.example.letstrip.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                    WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // ServerHttpRequest에서 세션 정보 가져오기
        String personId = (String) request.getHeaders().getFirst("personId");

        // WebSocket 세션의 attributes에 personId 저장
        if (personId != null) {
            attributes.put("personId", personId);
        }

        // WebSocket 연결 허용
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
