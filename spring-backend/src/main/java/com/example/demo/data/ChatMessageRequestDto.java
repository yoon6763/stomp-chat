package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequestDto {

    private Long userId;
    private String message;

    public ChatMessage toChatMessage() {
        return new ChatMessage(userId, message);
    }

}
