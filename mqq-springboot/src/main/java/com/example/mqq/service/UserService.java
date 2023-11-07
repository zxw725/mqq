package com.example.mqq.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mqq.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mqq.mapper.UserMapper;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
public interface UserService extends IService<User> {
    User userLogin(User userInfo);

    Map<String, Integer> addUserInfo(User userDataInfoVo);

    User selectUser(int id);
}
