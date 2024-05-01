package com.example.demo.service;

import com.example.demo.data.ChatMessage;
import com.example.demo.data.ChatMessageRequestDto;
import com.example.demo.data.ChatMessageResponseDto;
import com.example.demo.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatMessageResponseDto saveMessage(ChatMessageRequestDto chatMessageRequestDto) {
        ChatMessage chatMessage = chatRepository.saveMessage(chatMessageRequestDto);
        return ChatMessageResponseDto.from(chatMessage);
    }

}
