package com.example.mqq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author zxw
 * @since 2023年09月19日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 路由参数
     */
    @TableField("query")
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    @TableField("is_frame")
    private Integer isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @TableField("is_cache")
    private Integer isCache;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField("menu_type")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField("visible")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @TableField("status")
    private String status;

    /**
     * 权限标识
     */
    @TableField("perms")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField("update_at")
    private LocalDateTime updateAt;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
