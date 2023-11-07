package com.example.mqq.mapper;

import com.example.mqq.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2023年09月28日
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
