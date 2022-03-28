package com.example.websocketchatpoc.application.dto;

import com.example.websocketchatpoc.common.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
    private MessageType type;

    private String roomId;

    private String sender;

    private String message;
}
