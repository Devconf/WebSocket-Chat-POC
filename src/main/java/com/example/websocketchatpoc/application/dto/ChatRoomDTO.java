package com.example.websocketchatpoc.application.dto;

import com.example.websocketchatpoc.application.ChatService;
import com.example.websocketchatpoc.common.MessageType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomDTO(String roomId, String name){
        this.roomId=roomId;
        this.name=name;
    }

    public void handleActions(WebSocketSession session, ChatMessageDTO messageDTO, ChatService chatService){
        if(messageDTO.getType().equals(MessageType.ENTER)){
            this.sessions.add(session);
            messageDTO.setMessage(messageDTO.getSender() +"님이 입장하였습니다.");
        }
        if(messageDTO.getType().equals(MessageType.LEAVE)){
            this.sessions.remove(session);
            messageDTO.setMessage(messageDTO.getSender() +"님이 나갔습니다.");
        }
        sendMessage(messageDTO.getMessage(),chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService){
        this.sessions.parallelStream().forEach(session -> chatService.sendMessage(session,message));
    }
}
