package com.example.demo.data;

import lombok.Data;

@Data
public class ChatDto {

    private Long channelId;
    private Long userId;
    private String message;

}
