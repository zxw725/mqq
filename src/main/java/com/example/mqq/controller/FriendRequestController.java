package com.example.mqq.controller;

import com.example.mqq.entity.FriendRequest;
import com.example.mqq.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend-requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping
    public void sendFriendRequest(@RequestBody FriendRequest request) {
        friendRequestService.sendFriendRequest(request);
    }

    @GetMapping("/{userId}")
    public List<FriendRequest> getFriendRequests(@PathVariable String userId) {
        return friendRequestService.getFriendRequests(userId);
    }

    @DeleteMapping("/{userId}")
    public void removeFriendRequest(@PathVariable String userId, @RequestBody FriendRequest request) {
        friendRequestService.removeFriendRequest(userId, request);
    }
}
