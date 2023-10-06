package com.zyolv.cloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {

    private Integer id;
    private Date createTime;
    private Date updateTime;
    private Integer pid;
    private String name;
    private String code;
    private String tocode;
    private Integer type;
    private Integer starus;
    private Integer level;
    private List<PermissionEntity> children;
    private boolean select;
}
