package com.example.websocketchatpoc.application;

import com.example.websocketchatpoc.application.dto.ChatRoomDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDTO> chatRooms;

    @PostConstruct
    private void init(){
        this.chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRoom(){
        return new ArrayList<>(this.chatRooms.values());
    }

    public ChatRoomDTO findRoomById(String roomId){
        return this.chatRooms.get(roomId);
    }

    public ChatRoomDTO createRoom(String name){
        String randomId = UUID.randomUUID().toString();
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO(randomId,name);
        this.chatRooms.put(randomId,chatRoomDTO);
        return chatRoomDTO;
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        }
        catch (IOException e){
            log.error(e.getMessage(),e);
        }
    }
}
