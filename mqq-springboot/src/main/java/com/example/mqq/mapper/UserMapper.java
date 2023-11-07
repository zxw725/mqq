package com.example.mqq.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mqq.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    Page<User> selectPage(Page<Object> objectPage, Class<User> userClass, MPJLambdaWrapper<User> wrapper);
}
