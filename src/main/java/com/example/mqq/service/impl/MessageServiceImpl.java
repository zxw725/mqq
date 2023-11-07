package com.example.mqq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mqq.entity.Message;
import com.example.mqq.entity.User;
import com.example.mqq.entity.UserFriend;
import com.example.mqq.mapper.MessageMapper;
import com.example.mqq.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.toolkit.JoinWrappers;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxw
 * @since 2023年09月28日
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public Map<String, String> sendMessage(Message message){
        int insert = messageMapper.insert(message);
        Map<String, String> infoMap = new HashMap<>();

        if (insert >= 1) {
            String message1 = message.getMessage();
            infoMap.put("message", message1);
            return infoMap;
        } else {
            return null;
        }
//        return
    }

    @Override
    public List<Message> getAllMessage(int fid, int uid){
//        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("fid", fid);
//        queryWrapper.eq("uid", uid);
//        QueryWrapper<Message> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("fid", uid);
//        queryWrapper1.eq("uid", fid);
        val wrapper1 = JoinWrappers.lambda(Message.class)
                .selectAll(Message.class)
                .selectAll(User.class)
                .leftJoin(User.class, User::getId, Message::getUid)
                .eq(Message::getUid,uid)
                .eq(Message::getFid,fid);
        val wrapper2 = JoinWrappers.lambda(Message.class)
                .selectAll(Message.class)
                .selectAll(User.class)
                .leftJoin(User.class, User::getId, Message::getUid)
                .eq(Message::getUid,fid)
                .eq(Message::getFid,uid);
        List<Message> messageList = messageMapper.selectList(wrapper1);
        List<Message> messageList1 = messageMapper.selectList(wrapper2);
        messageList.addAll(messageList1);
        Collections.sort(messageList, Comparator.comparing(Message::getTime));
        return  messageList;
    }
}
