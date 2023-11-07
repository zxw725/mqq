package com.example.mqq.controller;


import com.auth0.jwt.interfaces.Claim;
import com.example.mqq.entity.User;
import com.example.mqq.service.UserService;
import com.example.mqq.token.PassToken;
import com.example.mqq.token.TokenTool;
import com.example.mqq.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Set<Integer> onlineUsers = ConcurrentHashMap.newKeySet();
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PassToken
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<?> userLogin(@RequestBody User userInfo){
        User userInfo1 = userService.userLogin(userInfo);
        System.out.println(userInfo.getId());
        if(userInfo1!=null){
            String key = "user:online:" + userInfo.getId();
            onlineUsers.add(userInfo1.getId());
//            System.out.println(123);
//            System.out.println(redisTemplate.opsForValue());
            redisTemplate.opsForValue().set(key, true);
        }
        String token = TokenTool.getToken(userInfo1);
        Result<String> result = Result.success(token);
        Map<String, Claim> stringClaimMap = TokenTool.verifyToken(token);
        return result;
    }


    @PassToken
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<?> addUserInfo(@RequestBody User userDataInfoVo){
        Map<String, Integer> infoMap = userService.addUserInfo(userDataInfoVo);
        if (infoMap != null){
            return Result.success(infoMap);
        }else {
            return Result.error("添加失败");
        }
    }

    @PassToken
    @RequestMapping(value = "/selectUser",method = RequestMethod.POST)
    public Result<?> selectUser(@RequestBody String id){
        int id1 = Integer.parseInt(id);
        User user = userService.selectUser(id1);
        if(user!= null){
            return Result.success(user);
        }else{
            return Result.error("没有该用户");
        }
    }



}

