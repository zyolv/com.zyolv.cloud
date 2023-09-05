package com.zyolv.cloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String fullname;
}
