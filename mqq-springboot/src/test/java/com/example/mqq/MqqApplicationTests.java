package com.example.mqq;

import com.example.mqq.entity.User;
import com.example.mqq.entity.UserFriend;
import com.example.mqq.mapper.UserMapper;
import com.github.yulichang.toolkit.JoinWrappers;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MqqApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        List<User> userList=userMapper.selectList(null);
        System.out.println(userList);
    }

    @Test
    void test1(){
        val wrapper = JoinWrappers.lambda(User.class)
                .selectAll(User.class)
                .leftJoin(UserFriend.class, UserFriend::getFid, User::getId)
                .eq(UserFriend::getUid,100001);
        System.out.println(wrapper);
        List<User> users = userMapper.selectList( wrapper);

        for (User record:users){
            System.out.println(record.getUsername());
        }
    }

}
