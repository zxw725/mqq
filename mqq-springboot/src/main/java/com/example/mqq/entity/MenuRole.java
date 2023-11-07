package com.example.mqq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("menu_role")
public class MenuRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("menu_id")
    private Integer menuId;

    @TableField("role_id")
    private Integer roleId;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;


}
