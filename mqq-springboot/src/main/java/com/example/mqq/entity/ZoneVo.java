package com.example.mqq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxw
 * @since 2023年11月20日
 */
@Getter
@Setter
@Accessors(chain = true)
public class ZoneVo implements Serializable {
    private String username;
    private String url;
    private Zone zone;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;



    public ZoneVo() {
    }

    public ZoneVo(String username, String url, Zone zone, LocalDateTime time) {
        this.username = username;
        this.url = url;
        this.zone = zone;
        this.time = time;
    }


}
