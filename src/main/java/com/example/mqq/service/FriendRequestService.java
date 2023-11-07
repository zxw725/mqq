package com.example.mqq.service;

import com.example.mqq.entity.FriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {

    private static final String REQUESTS_KEY_PREFIX = "friend_requests:";

    @Autowired
    private RedisTemplate<String, FriendRequest> redisTemplate;

    public void sendFriendRequest(FriendRequest request) {
        String key = REQUESTS_KEY_PREFIX + request.getReceiverId();
        redisTemplate.opsForList().leftPush(key, request);
    }

    public List<FriendRequest> getFriendRequests(String userId) {
        String key = REQUESTS_KEY_PREFIX + userId;
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public void removeFriendRequest(String userId, FriendRequest request) {
        String key = REQUESTS_KEY_PREFIX + userId;
        redisTemplate.opsForList().remove(key, 0, request);
    }
}

