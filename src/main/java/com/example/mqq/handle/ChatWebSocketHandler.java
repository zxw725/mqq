package com.example.mqq.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> userSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 用户连接建立时
        // 将用户ID和session关联
        String userId = session.getUri().getQuery();

        System.out.println("已经建立连接" + userId);
        System.out.println(userId);
        userSessions.put(userId, session);
        System.out.println(userSessions);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的消息
        String payload = message.getPayload();
        // 解析消息，获取目标用户ID和消息内容
        // 实际中可能需要定义消息格式，如JSON格式
        String type = extractType(payload);
        if (type == "1") {
            String targetUserId = extractTargetUserId(payload);
            String content = extractContent(payload);

            // 获取目标用户的session
            WebSocketSession targetSession = userSessions.get("userId=" + targetUserId);
            System.out.println(userSessions);
            System.out.println(userSessions.get("userId=" + targetUserId));
            if (targetSession != null && targetSession.isOpen()) {
                System.out.println(content);
                // 向目标用户发送消息
                targetSession.sendMessage(new TextMessage(content));
            }

        }


    }


    private String extractType(String payload) {
        String jsonString = payload.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String type = jsonNode.get("type").asText();
//        String content = jsonNode.get("content").asText();
        System.out.println(type);
        // 解析消息，获取目标用户ID
        // 实际中可能需要根据你的消息格式定义解析逻辑
        // 这里只是一个简单的示例
        return type;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 用户连接关闭时，将用户ID和session解除关联
        String userId = extractUserId(session);
        userSessions.remove(userId);
    }

    private String extractUserId(WebSocketSession session) {
        // 从session中获取用户ID，实际中可能需要根据你的身份验证逻辑获取用户ID
        // 这里只是一个简单的示例
        return session.getId();
    }

    private String extractTargetUserId(String payload) {
        String jsonString = payload.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String targetUserId = jsonNode.get("targetUserId").asText();
        String content = jsonNode.get("content").asText();
        System.out.println(targetUserId);
        // 解析消息，获取目标用户ID
        // 实际中可能需要根据你的消息格式定义解析逻辑
        // 这里只是一个简单的示例
        return targetUserId;
    }

    private String extractContent(String payload) {
        // 解析消息，获取消息内容
        // 实际中可能需要根据你的消息格式定义解析逻辑
        // 这里只是一个简单的示例
        return payload;
    }
}
