package com.example.mqq.controller;


import com.auth0.jwt.interfaces.Claim;
import com.example.mqq.entity.Message;
import com.example.mqq.entity.User;
import com.example.mqq.service.MessageService;
import com.example.mqq.token.PassToken;
import com.example.mqq.token.TokenTool;
import com.example.mqq.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023年09月28日
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @PassToken
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public Result<?> sendMessage(@RequestBody Message message){
        Map<String, String> infoMap = messageService.sendMessage(message);
        if (infoMap != null){
            return Result.success(infoMap);
        }else {
            return Result.error("发送失败");
        }
//        Message message1 = messageService.sendMessage(message);

    }
    @PassToken
    @RequestMapping(value = "/getAllMessage",method = RequestMethod.POST)
    public Result<?> selectUser(@RequestBody Message message){
//        int id1 = Integer.parseInt(fi);
        List<Message> messagesList = messageService.getAllMessage(message.getFid(),message.getUid());

            return Result.success(messagesList);

    }

}

