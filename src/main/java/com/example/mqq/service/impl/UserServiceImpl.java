package com.example.mqq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mqq.entity.User;
import com.example.mqq.mapper.UserMapper;
import com.example.mqq.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(User userInfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String username = userInfo.getUsername();
        int id = userInfo.getId();
        if (username != null) {
            queryWrapper.eq("username", username);
        }
        if (id != 0) {
            queryWrapper.eq("id", id);
        }
//        session.setAttribute("userIsOnline", true);
        queryWrapper.eq("password", userInfo.getPassword());
        User userInfo1 = userMapper.selectOne(queryWrapper);
        return userInfo1;
    }

    @Override
    public Map<String, Integer> addUserInfo(User userDataInfoVo) {
        int insert = userMapper.insert(userDataInfoVo);
        Map<String, Integer> infoMap = new HashMap<>();
        if (insert >= 1) {
            int id = userDataInfoVo.getId();
            infoMap.put("id", id);
            return infoMap;
        } else {
            return null;
        }
    }

    @Override
    public User selectUser(int id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        if(user!=null){
            return user;
        }else{
            return null;
        }
    }
}
