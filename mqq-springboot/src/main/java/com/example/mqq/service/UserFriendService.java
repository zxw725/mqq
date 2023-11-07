package com.example.mqq.service;

import com.example.mqq.entity.User;
import com.example.mqq.entity.UserFriend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
public interface UserFriendService extends IService<UserFriend> {

    List<User> selectAllFriends(int id);
}
