package com.example.mqq.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FriendRequest {
    private String senderId;
    private String receiverId;
    private String message;

    // 构造函数、getter和setter方法省略
}
