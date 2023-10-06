package com.zyolv.cloud.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role_routes")
public class RoleRoutes {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("role_id")
    private Integer roleId;

    private String routes;
}
