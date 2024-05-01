package com.example.demo.controller;

import com.example.demo.data.ChatMessageRequestDto;
import com.example.demo.data.ChatMessageResponseDto;
import com.example.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebSocketController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpleMessageTemplate;

    @MessageMapping("/chat")
    public ResponseEntity<String> receiveMessage(@RequestBody ChatMessageRequestDto chatMessageRequestDto) {
        ChatMessageResponseDto chatMessageResponseDto = chatService.saveMessage(chatMessageRequestDto);

        simpleMessageTemplate.convertAndSend("/sub/chatroom" + chatMessageRequestDto.getUserId(), chatMessageResponseDto);
        return ResponseEntity.ok("Message sent to the chatroom");
    }
}
