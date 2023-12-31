package com.zyolv.cloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: zhurongsheng
 * @Date: 2020/7/10 01:14
 */
@TableName("t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String name;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "update_time")
    private Date updateTime;

    private String phone;

    private String rolename;

}
