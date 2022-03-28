package com.example.websocketchatpoc.controller;

import com.example.websocketchatpoc.application.ChatService;
import com.example.websocketchatpoc.application.dto.ChatRoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping(value = "/chat")
    public ChatRoomDTO createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }
    
    @GetMapping(value = "/chat")
    public List<ChatRoomDTO> finaAllRoom(){
        return chatService.findAllRoom();
    }
}
