package com.example.demo.controller;

import com.example.demo.data.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(ChatDto chatDto, SimpMessageHeaderAccessor accessor) {
        log.info("chatDto: {}", chatDto);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatDto.getChannelId(), chatDto);
    }
}
