package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageResponseDto {

    private Long userId;
    private String message;

    public static ChatMessageResponseDto from(ChatMessage chatMessage) {
        return ChatMessageResponseDto.builder()
                .userId(chatMessage.getUserId())
                .message(chatMessage.getMessage())
                .build();
    }
}
