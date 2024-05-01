package com.example.demo.repository;

import com.example.demo.data.ChatMessage;
import com.example.demo.data.ChatMessageRequestDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ChatRepository {

    private final Map<Long, ChatMessage> chatMessages = new HashMap<>();
    private Long id = 0L;

    public ChatMessage saveMessage(ChatMessageRequestDto chatMessageRequestDto) {
        ChatMessage chatMessage = chatMessageRequestDto.toChatMessage();
        chatMessages.put(id, chatMessage);
        id++;
        return chatMessage;
    }

}
