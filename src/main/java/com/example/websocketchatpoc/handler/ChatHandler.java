package com.example.websocketchatpoc.handler;

import com.example.websocketchatpoc.application.ChatService;
import com.example.websocketchatpoc.application.dto.ChatMessageDTO;
import com.example.websocketchatpoc.application.dto.ChatRoomDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Log4j2
@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : "+payload);
        ChatMessageDTO chatMessageDTO = objectMapper.readValue(payload,ChatMessageDTO.class);
        ChatRoomDTO room = chatService.findRoomById(chatMessageDTO.getRoomId());
        room.handleActions(session,chatMessageDTO,chatService);
    }
}
