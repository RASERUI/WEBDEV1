package com.rslrui.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.rslrui.demo.dto.Messages;

@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage") // clients send to /app/sendMessage
    public void receiveMessage(Messages message) {
        messagingTemplate.convertAndSend("/topic/channel1", message);
    }
}
