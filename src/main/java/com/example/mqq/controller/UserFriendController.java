package com.example.mqq.controller;


import com.example.mqq.entity.User;
import com.example.mqq.service.UserFriendService;
import com.example.mqq.token.PassToken;
import com.example.mqq.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
@RestController
@RequestMapping("/userFriend")
public class UserFriendController {
    @Autowired
    private UserFriendService userFriendService;
    @PassToken
    @RequestMapping(value = "/selectAllFriends",method = RequestMethod.POST)
    public Result<?> selectUser(@RequestBody int id){
        List<User> user = userFriendService.selectAllFriends(id);
        if(user!= null){
            return Result.success(user);
        }else{
            return Result.error("没有该用户");
        }
    }
}

