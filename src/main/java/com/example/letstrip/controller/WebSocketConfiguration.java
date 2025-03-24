package com.example.letstrip.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.letstrip.handler.ChatWebSocketHandler;
import com.example.letstrip.interceptor.WebSocketInterceptor;
    
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebSocketHandler(), "/chat")
        		.addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*"); // 모든 도메인에서 접근 허용
    }
}
