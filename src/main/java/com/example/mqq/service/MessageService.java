package com.example.mqq.service;

import com.example.mqq.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxw
 * @since 2023年09月28日
 */
public interface MessageService extends IService<Message> {

    Map<String, String> sendMessage(Message message);

    List<Message> getAllMessage(int fid, int uid);
}
