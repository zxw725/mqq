package com.example.mqq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mqq.entity.User;
import com.example.mqq.entity.UserFriend;
import com.example.mqq.mapper.UserFriendMapper;
import com.example.mqq.mapper.UserMapper;
import com.example.mqq.service.UserFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.toolkit.JoinWrappers;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
@Service
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements UserFriendService {
    @Autowired
    private UserFriendMapper userFriendMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectAllFriends(int id){
        val wrapper = JoinWrappers.lambda(User.class)
                .selectAll(User.class)
                .leftJoin(UserFriend.class, UserFriend::getFid, User::getId)
                .eq(UserFriend::getUid,id);
        System.out.println(wrapper);
        List<User> users = userMapper.selectList(wrapper);

        for (User record:users){
            System.out.println(record.getUsername());
        }
        if(users!=null){
            return users;
        }else{
            return null;
        }
    }
}
