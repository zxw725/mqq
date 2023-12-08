package com.example.mqq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_friend")
public class UserFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("uid")
    private Integer uid;

    @TableField("fid")
    private Integer fid;

    @TableField("nickname")
    private String nickname;


}
